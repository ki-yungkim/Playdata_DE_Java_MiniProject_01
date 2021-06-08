package com.work.service;

import java.util.ArrayList;

import com.work.exception.DuplicateException;
import com.work.exception.RecordNotFoundException;
import com.work.model.dto.VaccineCount;

/**
 * <pre>
 * VaccineCount 구현 서비스 클래스 
 * </pre>
 * 
 * @author 김기영
 * @version ver 1.0
 * @since jdk1.8
 */
public class VaccineCountService {

	private ArrayList<VaccineCount> list = new ArrayList<VaccineCount>();
	private ArrayList existDayList = new ArrayList();
	private ArrayList existRegionList = new ArrayList();

	/** 기본생성자 */
	public VaccineCountService() {}

	/**
	 * 중복체크
	 * 고유 번호 존재 유무 조회
	 * @param idKey 고유 번호
	 * @return 존재하면 index 번호, 없으면 -1
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
	 * 중복체크
	 * 날짜 존재 유무 조회 
	 * @param day 날짜 
	 * @return 있으면 index 번호, 없으면 -1
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
	 * 입력 날짜에 해당하는 여러개 정보 리스트에 저장
	 * @param day 날짜 
	 * @return existDayList
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
	 * 중복 체크
	 * 날짜 존재 유무 조회
	 * @param region 지역
	 * @return 있으면 index 번호, 없으면 -1
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
	 * 입력 지역에 해당하는 여러개 정보 리스트에 저장
	 * @param region 지역
	 * @return existRegionList
	 */
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
	 * 백신 접종수 정보 추가 
	 * @param dto 접종 수  
	 * @throws DuplicateException 중복 예외
	 */
	public void addVaccineImfo(VaccineCount dto) throws DuplicateException  {
		int index = existidKey(dto.getIdKey());
		if (index >= 0) {
			throw new DuplicateException(Integer.toString(dto.getIdKey()));
		}

		list.add(dto);
	}

	/**
	 * 접종 수 초기화 등록 
	 * @return 리스트 크기
	 * @throws DuplicateException 중복 예외
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
		VaccineCount dto12 = new VaccineCount(12,"21-06-03", "서울특별시", 73063, 6277, 1165736, 346936);
		VaccineCount dto13 = new VaccineCount(13,"21-06-03", "부산광역시", 31246, 1313, 470503, 132160);
		VaccineCount dto14 = new VaccineCount(14,"21-06-03", "대구광역시", 15688, 929, 274006, 99038 );
		VaccineCount dto15 = new VaccineCount(15,"21-06-03", "인천광역시", 18911, 746, 333493, 104412 );
		VaccineCount dto16 = new VaccineCount(16,"21-06-03", "대전광역시", 10312, 1024, 183718, 61898);
		VaccineCount dto17 = new VaccineCount(17,"21-06-03", "광주광역시", 10075, 685, 204788, 69938);
		VaccineCount dto18 = new VaccineCount(18,"21-06-03", "울산광역시", 6611, 222, 116548, 36528);
		VaccineCount dto19 = new VaccineCount(19,"21-06-03", "세종특별자치시", 1408, 72, 32896, 10589);
		VaccineCount dto20 = new VaccineCount(20,"21-06-03", "경기도", 74413, 4772, 1467476, 473032);
		
		VaccineCount dto21 = new VaccineCount(21,"21-06-08", "전국", 855642, 19856, 8455799, 2299853);
		VaccineCount dto22 = new VaccineCount(22,"21-06-08", "서울특별시", 158574, 5333, 1500832, 372339);
		VaccineCount dto23 = new VaccineCount(23,"21-06-08", "부산광역시", 65946, 1277, 610662, 136775);
		VaccineCount dto24 = new VaccineCount(24,"21-06-08", "대구광역시", 34635, 242, 345733, 100888);
		VaccineCount dto25 = new VaccineCount(25,"21-06-08", "인천광역시", 45278, 710, 424475, 107161);
		VaccineCount dto26 = new VaccineCount(26,"21-06-08", "대전광역시", 21646, 866, 227476, 64712);
		VaccineCount dto27 = new VaccineCount(27,"21-06-08", "광주광역시", 24458, 1253, 250298, 73493);
		VaccineCount dto28 = new VaccineCount(28,"21-06-08", "울산광역시", 16788, 710, 147905, 37688);
		VaccineCount dto29 = new VaccineCount(29,"21-06-08", "세종특별자치시", 4319, 135, 40585, 11075);
		VaccineCount dto30 = new VaccineCount(30,"21-06-08", "경기도", 194416, 2528, 1851482, 487489);
		
		addVaccineImfo(dto1);
		addVaccineImfo(dto2);
		addVaccineImfo(dto3);
		addVaccineImfo(dto4);
		addVaccineImfo(dto5);
		addVaccineImfo(dto6);
		addVaccineImfo(dto7);
		addVaccineImfo(dto8);
		addVaccineImfo(dto9);
		addVaccineImfo(dto10);
		
		addVaccineImfo(dto11);
		addVaccineImfo(dto12);
		addVaccineImfo(dto13);
		addVaccineImfo(dto14);
		addVaccineImfo(dto15);
		addVaccineImfo(dto16);
		addVaccineImfo(dto17);
		addVaccineImfo(dto18);
		addVaccineImfo(dto19);
		addVaccineImfo(dto20);

		addVaccineImfo(dto21);
		addVaccineImfo(dto22);
		addVaccineImfo(dto23);
		addVaccineImfo(dto24);
		addVaccineImfo(dto25);
		addVaccineImfo(dto26);
		addVaccineImfo(dto27);
		addVaccineImfo(dto28);
		addVaccineImfo(dto29);
		addVaccineImfo(dto30);
		return list.size();
	}

	/**
	 * 추가 등록 
	 * @param idKey 고유번호
	 * @param day 날짜 
	 * @param region 지역
	 * @param yesterdayFirst 전날 1차 접종 수
	 * @param yesterdaySecond 전날 2차 접종 수
	 * @param totalFirst 누적 접종수
	 * @param totalSecond 누적 접종수
	 * @throws DuplicateException 중복 예외
	 */
	public void addVacciCount(int idKey, String day, String region, int yesterdayFirst, int yesterdaySecond, int totalFirst, int totalSecond) throws DuplicateException {
		VaccineCount dto = new VaccineCount(idKey, day, region, yesterdayFirst, yesterdaySecond, totalFirst, totalSecond);

		addVaccineImfo(dto);
	} 	

	/**
	 * 전체 조회
	 * @return list
	 */
	public ArrayList<VaccineCount> getList() {
		return list;
	}

	/**
	 * 리스트 크기 조회
	 * @return list 크기
	 */
	public int getCount() {
		return list.size();
	}

	/**
	 * 지역으로 리스트 조회
	 * @param region 지역
	 * @return 지역에 해당하는 리스트 
	 * @throws RecordNotFoundException 데이터 검색 예외
	 */
	public VaccineCount getRegionCount(String region) throws RecordNotFoundException {
		int index = existRegion(region);
		if (index >= 0) {
			return (VaccineCount)list.get(index);
		} 
		throw new RecordNotFoundException(region);
	}

	/**
	 * 지역으로 전날 1차 접종자 수 조회
	 * @param region 지역
	 * @return 지역에 해당하는 전날 1차 접종자 수 
	 * @throws RecordNotFoundException 데이터 검색 예외
	 */
	public int getRegionCountFirst(String region) throws RecordNotFoundException {
		int index = existRegion(region);
		if (index >= 0) {
			return list.get(index).getYesterdayFirst();
		} 
		throw new RecordNotFoundException(region);
	}

	/**
	 * 날짜, 지역으로 1차 접종자 수 조회
	 * 동일 날짜, 동일 지역이 많으므로 둘의 index가 일치하는 리스트 조회해서 반환
	 * @param day 날짜
	 * @param region 지역
	 * @return 전날 1차 접종자 수, 없으면 -1
	 */
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
	 * 지역에 해당하는 전날 2차 접종자 수 조회
	 * @param region 지역
	 * @return 2차 접종자 수 
	 * @throws RecordNotFoundException 데이터 검색 예외
	 */
	public int getRegionCountSecond(String region) throws RecordNotFoundException {
		int index = existRegion(region);
		if (index >= 0) {
			return list.get(index).getYesterdaySecond();
		} 
		throw new RecordNotFoundException(region);
	}
	
	/**
	 * 날짜, 지역으로 2차 접종자 수 조회
	 * 동일 날짜, 동일 지역이 많으므로 둘의 index가 일치하는 리스트 조회해서 반환
	 * @param day 날짜
	 * @param region 지역
	 * @return 2차 접종자 수, 없으면 -1
	 * @throws RecordNotFoundException 데이터 검색 예외
	 */
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
	 * 지역 정보와 맞는 누적 1차 접종자 수
	 * @param region 지역
	 * @return 누적 1차 접종자 수
	 * @throws RecordNotFoundException 데이터 검색 예외
	 */
	public int getRegionCountTotalFirst(String region) throws RecordNotFoundException {
		int index = existRegion(region);
		if (index >= 0) {
			return list.get(index).getTotalFirst();
		} 
		throw new RecordNotFoundException(region);
	}

	/**
	 * 날짜, 지역 정보에 해당하는 누적 1차 접종자 수
	 * @param day 날짜
	 * @param region 지역
	 * @return 누적 1차 접종자 수
	 * @throws RecordNotFoundException 데이터 검색 예외
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
	 * 지역 정보와 맞는 누적 2차 접종자 수
	 * @param region 지역
	 * @return 2차 접종자 수
	 * @throws RecordNotFoundException 데이터 검색 예외
	 */
	public int getRegionCountTotalSecond(String region) throws RecordNotFoundException {
		int index = existRegion(region);
		if (index >= 0) {
			return list.get(index).getTotalSecond();
		} 
		throw new RecordNotFoundException(region);
	}

	/**
	 * 날짜, 지역 정보에 해당하는 누적 2차 접종자 수
	 * @param day 날짜
	 * @param region 지역
	 * @return 누적 2차 접종자 수 
	 * @throws RecordNotFoundException 데이터 검색 예외
	 */
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
	 * 리스트 내용 삭제 
	 * @return 리스트 크기
	 */
	public int removeCountAll() {
		list.clear();
		return list.size();
	}

	/**
	 * 날짜, 지역에 해당하는 리스트 삭제
	 * @param day 날짜
	 * @param region 지역
	 * @return 리스트 삭제 
	 * @throws RecordNotFoundException 데이터 검색 예외
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
	 * 접종자 수 데이터 변경
	 * @param dto 접종자 수 데이터
	 * @return 지역, 날짜가 맞으면 true;
	 * @throws RecordNotFoundException 데이터 검색 예외
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