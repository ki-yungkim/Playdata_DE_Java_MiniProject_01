package com.work.service;

import java.util.ArrayList;

import com.work.exception.DuplicateException;
import com.work.exception.RecordNotFoundException;
import com.work.model.dto.VaccineCount;


public class VaccineCountService {

	/**
	 * 
	 */
	private ArrayList<VaccineCount> list = new ArrayList<VaccineCount>();
	private ArrayList existDayList = new ArrayList();
	private ArrayList existRegionList = new ArrayList();

	/**
	 * 
	 */
	public VaccineCountService() {}

	/**
	 * 
	 * @param idKey
	 * @return
	 */
	public int existidKey(int idKey) {
		for (int index = 0; index < list.size(); index++) {
			if (list.get(index).getIdKey() == idKey) {
				return index;
			}
		}
		return -1;
	}

	/**
	 * 
	 * @param day
	 * @return
	 */
	public int existDay(String day) {
		for (int index = 0; index < list.size(); index++) {
			if (list.get(index).getDay().equals(day)) {
				return index;
			}
		}
		return -1;
	}

	/**
	 * 
	 * @param day
	 * @return
	 */
	public ArrayList existDayList(String day) {

		existDayList.clear();
		for (int index = 0; index < list.size(); index++) {
			if (list.get(index).getDay().equals(day)) {
				existDayList.add(index);
			}
		}
		return existDayList;
	}


	/**
	 * 
	 * @param resion
	 * @return
	 */
	public int existRegion(String region) {
		for (int index = 0; index < list.size(); index++) {
			if (list.get(index).getRegion().equals(region)) {
				return index;
			}
		}
		return -1;
	}

	public ArrayList existRegionList(String region) {

		existRegionList.clear();
		for (int index = 0; index < list.size(); index++) {
			if (list.get(index).getRegion().equals(region)) {
				existRegionList.add(index);
			}
		}
		return existRegionList;
	}

	/**
	 * 
	 * @param dto
	 * @throws DuplicateException
	 */
	public void addCenter(VaccineCount dto) throws DuplicateException  {
		int index = existidKey(dto.getIdKey());
		if (index >= 0) {
			throw new DuplicateException(Integer.toString(dto.getIdKey()));
		}

		list.add(dto);
	}

	/**
	 * 
	 * @return
	 * @throws DuplicateException
	 */
	public int initCount() throws DuplicateException {
		VaccineCount dto1 = new VaccineCount(1, "21-06-02", "전국", 565377, 25945, 6358512, 2198010);
		VaccineCount dto2 = new VaccineCount(2, "21-06-02", "서울특별시", 103600, 7109, 1092306, 340624);
		VaccineCount dto3 = new VaccineCount(3, "21-06-02", "부산광역시", 44891, 1507, 439138, 130833);
		VaccineCount dto4 = new VaccineCount(4, "21-06-02", "대구광역시", 22868, 1221, 258241, 98109);
		VaccineCount dto5 = new VaccineCount(5, "21-06-02", "인천광역시", 28655, 868, 314522, 103649);
		VaccineCount dto6 = new VaccineCount(6, "21-06-02", "대전광역시", 14360, 1013, 173394, 60816);
		VaccineCount dto7 = new VaccineCount(7, "21-06-02", "광주광역시", 16806, 795, 194653, 69215);
		VaccineCount dto8 = new VaccineCount(8, "21-06-02", "울산광역시", 10701, 122, 109906, 36305);
		VaccineCount dto9 = new VaccineCount(9, "21-06-02", "세종특별자치시", 2247, 42, 31476, 10517);
		VaccineCount dto10 = new VaccineCount(10, "21-06-02", "강원도", 19585, 1049, 245683, 81041);
		VaccineCount dto11 = new VaccineCount(11,"21-06-03", "전국", 381551, 22229, 6741993, 2220728);
		addCenter(dto1);
		addCenter(dto2);
		addCenter(dto3);
		addCenter(dto4);
		addCenter(dto5);
		addCenter(dto6);
		addCenter(dto7);
		addCenter(dto8);
		addCenter(dto9);
		addCenter(dto10);
		addCenter(dto11);

		return list.size();
	}


	/**
	 * @param idKey
	 * @param day
	 * @param region
	 * @param yesterdayFirst
	 * @param yesterdaySecond
	 * @param totalFirst
	 * @param totalSecond
	 * @throws DuplicateException
	 */
	public void addVacciCount(int idKey, String day, String region, int yesterdayFirst, int yesterdaySecond, int totalFirst, int totalSecond) throws DuplicateException {
		VaccineCount dto = new VaccineCount(idKey, day, region, yesterdayFirst, yesterdaySecond, totalFirst, totalSecond);

		addCenter(dto);
	} 	

	/**
	 * 
	 * @return
	 */
	public ArrayList<VaccineCount> getList() {
		return list;
	}

	/**
	 * 
	 * @return
	 */
	public int getCount() {
		return list.size();
	}

	/**
	 * 
	 * @param region
	 * @return
	 * @throws RecordNotFoundException
	 */
	public VaccineCount getRegionCount(String region) throws RecordNotFoundException {
		int index = existRegion(region);
		if (index >= 0) {
			return (VaccineCount)list.get(index);
		} 
		throw new RecordNotFoundException(region);
	}

	/**
	 * 
	 * @param region
	 * @return
	 * @throws RecordNotFoundException
	 */
	public int getRegionCountFirst(String region) throws RecordNotFoundException {
		int index = existRegion(region);
		if (index >= 0) {
			return list.get(index).getYesterdayFirst();
		} 
		throw new RecordNotFoundException(region);
	}

	public int getRegionCountFirst(String day, String region) {

		existDayList(day);
		existRegionList(region);

		for (int index = 0; index < existDayList.size(); index++) {
			for (int i = 0; i < existRegionList.size(); i++) {
				int indexDay = (int)existDayList(day).get(index);
				int indexRegion = (int)existRegionList(region).get(i);
				if(indexDay >= 0 && indexRegion >=0) {
					if(indexDay == indexRegion) {
						return list.get(indexDay).getYesterdayFirst();
					}
				}
			}
		}
		return -1;
	}
	/**
	 * 
	 * @param region
	 * @return
	 * @throws RecordNotFoundException
	 */
	public int getRegionCountSecond(String region) throws RecordNotFoundException {
		int index = existRegion(region);
		if (index >= 0) {
			return list.get(index).getYesterdaySecond();
		} 
		throw new RecordNotFoundException(region);
	}

	public int getRegionCountSecond(String day, String region) throws RecordNotFoundException {

		existDayList(day);
		existRegionList(region);

		for (int index = 0; index < existDayList.size(); index++) {
			for (int i = 0; i < existRegionList.size(); i++) {
				int indexDay = (int)existDayList(day).get(index);
				int indexRegion = (int)existRegionList(region).get(i);
				if(indexDay >= 0 && indexRegion >=0) {
					if(indexDay == indexRegion) {
						return list.get(indexDay).getYesterdayFirst();
					}
				}
			}
		}
		return -1;
	}

	/**
	 * 
	 * @param region
	 * @return
	 * @throws RecordNotFoundException
	 */
	public int getRegionCountTotalFirst(String region) throws RecordNotFoundException {
		int index = existRegion(region);
		if (index >= 0) {
			return list.get(index).getTotalFirst();
		} 
		throw new RecordNotFoundException(region);
	}

	/**
	 * 
	 * @param day
	 * @param region
	 * @return
	 * @throws RecordNotFoundException
	 */
	public int getRegionCountTotalFirst(String day, String region) throws RecordNotFoundException {

		existDayList(day);
		existRegionList(region);

		for (int index = 0; index < existDayList.size(); index++) {
			for (int i = 0; i < existRegionList.size(); i++) {
				int indexDay = (int)existDayList(day).get(index);
				int indexRegion = (int)existRegionList(region).get(i);
				if(indexDay >= 0 && indexRegion >=0) {
					if(indexDay == indexRegion) {
						return list.get(indexDay).getTotalFirst();
					}
				}
			}
		}
		return -1;
	}
	/**
	 * 
	 * @param region
	 * @return
	 * @throws RecordNotFoundException
	 */
	public int getRegionCountTotalSecond(String region) throws RecordNotFoundException {
		int index = existRegion(region);
		if (index >= 0) {
			return list.get(index).getTotalSecond();
		} 
		throw new RecordNotFoundException(region);
	}

	public int getRegionCountTotalSecond(String day, String region) throws RecordNotFoundException {
		existDayList(day);
		existRegionList(region);

		for (int index = 0; index < existDayList.size(); index++) {
			for (int i = 0; i < existRegionList.size(); i++) {
				int indexDay = (int)existDayList(day).get(index);
				int indexRegion = (int)existRegionList(region).get(i);
				if(indexDay >= 0 && indexRegion >=0) {
					if(indexDay == indexRegion) {
						return list.get(indexDay).getTotalSecond();
					}
				}
			}
		}
		return -1;
	}
	

	/**
	 * 헤당 일자 전체 내용 
	 * 
	 */


	/**
	 * 
	 * @return
	 */
	public int removeCountAll() {
		list.clear();
		return list.size();

	}

	/**
	 * 
	 * @param day
	 * @param region
	 * @return
	 * @throws RecordNotFoundException
	 */
	public VaccineCount removeCount(String day, String region) throws RecordNotFoundException{
		// 아마 exist에서 for문 필요 
		int indexDay = existDay(day);
		int indexRegion = existRegion(region);
		if (indexDay >= 0) {
			if (indexDay == indexRegion) {
				return list.remove(indexDay);
			}
		}
		throw new RecordNotFoundException(day);
	}

	/**
	 * 
	 * @param dto
	 * @return
	 * @throws RecordNotFoundException
	 */
	public boolean setCount(VaccineCount dto) throws RecordNotFoundException {
		// 아마 exist에서 for문 필요 
		int indexDay = existDay(dto.getDay());
		int indexRegion = existRegion(dto.getRegion());
		if (indexRegion >= 0) {
			if (indexDay == indexRegion) {
				list.set(indexDay, dto);
				return true;
			}
		}
		throw new RecordNotFoundException(dto.getRegion());
	}


	

}




