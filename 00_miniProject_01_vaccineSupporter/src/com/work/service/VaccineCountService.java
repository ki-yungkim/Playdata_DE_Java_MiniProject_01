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

	/**
	 * 
	 */
	public VaccineCountService() {}

	
	public int existDay(String day) {
		for (int index = 0; index < list.size(); index++) {
			if (list.get(index).getRegion().equals(day)) {
				return index;
			}
		}
		return -1;
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

	
	/**
	 * 
	 * @param dto
	 * @throws DuplicateException
	 */
	public void addCenter(VaccineCount dto) throws DuplicateException  {
		int index = existRegion(dto.getRegion());
		if (index >= 0) {
			throw new DuplicateException(dto.getRegion());
		}

		list.add(dto);
	}

	/**
	 * 
	 * @return
	 * @throws DuplicateException
	 */
	public int initCount() throws DuplicateException {
		VaccineCount dto1 = new VaccineCount("2021-06-02", "전국", 565377, 25945, 6358512, 2198010);
		VaccineCount dto2 = new VaccineCount("2021-06-02", "서울특별시", 565377, 25945, 6358512, 2198010);
		VaccineCount dto3 = new VaccineCount("2021-06-02", "부산광역시", 565377, 25945, 6358512, 2198010);
		VaccineCount dto4 = new VaccineCount("2021-06-02", "대구광역시", 565377, 25945, 6358512, 2198010);
		VaccineCount dto5 = new VaccineCount("2021-06-02", "인천광역시", 565377, 25945, 6358512, 2198010);
		VaccineCount dto6 = new VaccineCount("2021-06-02", "대전광역시", 565377, 25945, 6358512, 2198010);
		VaccineCount dto7 = new VaccineCount("2021-06-02", "광주광역시", 565377, 25945, 6358512, 2198010);
		VaccineCount dto8 = new VaccineCount("2021-06-02", "울산광역시", 565377, 25945, 6358512, 2198010);
		VaccineCount dto9 = new VaccineCount("2021-06-02", "세종특별자치시", 565377, 25945, 6358512, 2198010);
		VaccineCount dto10 = new VaccineCount("2021-06-02", "강원도", 565377, 25945, 6358512, 2198010);

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

		return list.size();
	}


	/**
	 * 
	 * @param day
	 * @param region
	 * @param yesterdayFirst
	 * @param yesterdaySecond
	 * @param totalFirst
	 * @param totalSecond
	 * @throws DuplicateException
	 */
	public void addVacciCount(String day, String region, int yesterdayFirst, int yesterdaySecond, int totalFirst, int totalSecond) throws DuplicateException {
		VaccineCount dto = new VaccineCount(day, region, yesterdayFirst, yesterdaySecond, totalFirst, totalSecond);

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
//	일자 전체 출력 메서드
//	public VaccineCount getDay(String day) {
//	}
	
	
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




