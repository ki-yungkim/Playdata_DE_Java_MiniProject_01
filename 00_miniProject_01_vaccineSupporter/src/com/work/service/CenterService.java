package com.work.service;

import java.util.ArrayList;

import com.work.exception.DuplicateException;
import com.work.exception.RecordNotFoundException;
import com.work.model.dto.CenterList;

/**
 * <pre>
 * CenterList 구현 서비스 클래스 
 * </pre>
 * @author 김기영
 * @version ver 1.0
 * @since jdk 1.8
 *
 */
public class CenterService {

	/** 센터 정보들을 저장관리하기 위한 자료 저장구조 : Generic */
	private ArrayList<CenterList> list = new ArrayList<CenterList>();
	private ArrayList sameRegionList = new ArrayList();
	
	/** 기본생성자 : 초기 센터 목록 등록 수행 */
	public CenterService() {}


	/**
	 * 센터 리스트에 센터 이름 존재 유무 조회
	 * @param centerName 센터 이름
	 * @return 존재하면 index 번호, 없으면 -1
	 */
	public int existCenterName(String centerName) {
		for (int index = 0; index < list.size(); index++) {
			if (list.get(index).getCenterName().equals(centerName)) {
				return index;
			}
		}
		return -1;
	}

	/**
	 * 센터 리스트에 시설 이름 존재 유무 조회
	 * @param facilityName 시설 이름
	 * @return 존재하면 index 번호, 없으면 -1
	 */
	public int existFacilityName(String facilityName) {
		for (int index = 0; index < list.size(); index++) {
			if (list.get(index).getFacilityName().equals(facilityName)) {
				return index;
			}
		}
		return -1;
	}

	/**
	 * 센터 리스트에 우편번호 존재 유무 조회
	 * @param postcode 우편번호
	 * @return 존재하면 index 번호, 없으면 -1
	 */
	public int existPostcode(String postcode) {
		for (int index = 0; index < list.size(); index++) {
			if (list.get(index).getPostcode().equals(postcode)) {
				return index;
			}
		}
		return -1;
	}

	/**
	 * 센터 리스트에 주소(도, 시, 구 단위까지) 존재 유무 조회
	 * @param address 주소
	 * @return 존재시 저장위치 인덱스번호, 미존재시 -1
	 */
	public int existAddress(String address) {
		for (int index = 0; index < list.size(); index++) {
			if (list.get(index).getAddress().equals(address)) {
				return index;
			}
		}
		return -1;
	}

	/**
	 * 간략 주소로 센터 정보 조회
	 * 같은 지역에 존재하는 여러 센터 출력을 위해 arraylist 사용 
	 * 입력한 주소(시군구)에 맞는 정보들 sameRegionList에 저장
	 * @param address 주소
	 * @return sameRegionList
	 */
	public ArrayList existAddressList(String address) {

		sameRegionList.clear();
		for (int index = 0; index < list.size(); index++) {
			if (list.get(index).getAddress().equals(address)) {
				sameRegionList.add(index);
			}
		}
		return sameRegionList;
	}


	/**
	 * 센터 리스트에 전화번호 존재 유무 조회
	 * @param phoneNumber 센터 전화번호 
	 * @return 존재시 저장위치 인덱스번호, 미존재시 -1
	 */
	public int existPhoneNumber(String phoneNumber) {
		for (int index = 0; index < list.size(); index++) {
			if (list.get(index).getPhoneNumber().equals(phoneNumber)) {
				return index;
			}
		}
		return -1;
	}

	/**
	 * 센터등록	
	 * @param dto 센터 정보 
	 * @throws DuplicateException 중복 예외
	 */
	public void addCenter(CenterList dto) throws DuplicateException  {
		int index = existPhoneNumber(dto.getPhoneNumber());
		if (index >= 0) {
			throw new DuplicateException(dto.getPhoneNumber());
		}

		list.add(dto);
	}

	/**
	 * 초기화 등록 
	 * @return 리스트 크기 
	 * @throws DuplicateException 중복 예외
	 */
	public int initCenter() throws DuplicateException {
		CenterList dto1 = new CenterList("강원도 평창군 예방접종센터", "평창읍 생활체육관", "25377", "강원도 평창군", "강원도 평창군 평창읍 산책길 38", "033-330-4835");
		CenterList dto2 = new CenterList("경기도 시흥시 예방접종센터", "정왕평생학습관", "15055", "경기도 시흥시", "경기도 시흥시 정왕대로 233번길 21", "031-310-5851");
		CenterList dto3 = new CenterList("경상남도 산청군 예방접종센터", "산청군 실내체육관", "52215", "경상남도 산청군", "경상남도 산청군 금서면 친환경로2631번길 39", "055-970-7548");
		CenterList dto4 = new CenterList("광주광역시 광산구 예방접종센터", "광주보훈병원 재활체육관", "62284", "광주광역시 광산구", "광주광역시 광산구 첨단월봉로 99, 광주보훈병원", "062-960-6862");
		CenterList dto5 = new CenterList("중앙 예방접종센터", "국립중앙의료원 D동", "4562", "서울특별시 중구", "서울특별시 중구 을지로 39길 29", "02-2260-7114");
		CenterList dto6 = new CenterList("서울특별시 중구 예방접종센터", "충무스포츠센터", "4569", "서울특별시 중구", "서울특별시 중구 퇴계로 387", "02-3396-4503");
		CenterList dto7 = new CenterList("강원도 강릉시 예방접종센터", "강릉아레나", "25377", "강원도 평창군", "강원도 평창군 평창읍 산책길 38", "033-330-4835");
		CenterList dto8 = new CenterList("경기도 안산시 단원구 예방접종센터", "올림픽기념관 체육관", "15335", "경기도 안산시", "경기도 안산시 단원구 적금로202", "031-369-1702");
		CenterList dto9 = new CenterList("경상북도 고령군 예방접종센터", "주산체육관", "40136", "경상북도 고령군", "경상북도 고령군 대가야읍 주산순환길 91", "054-950-7950");
		CenterList dto10 = new CenterList("충청남도 천안시 서북구 예방접종센터", "천안시 실내테니스장", "31157", "충청남도 천안시", "충청남도 천안시 서북구 번영로 208", "041-521-3013");
		CenterList dto11 = new CenterList("충청북도 청주시 서원구 예방접종센터", "청주체육관", "28559", "충청북도 청주시", "충청북도 청주시 서원구 사직대로 229", "043-201-3253");

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
	 * 센터 추가등록
	 * @param centerName 센터명
	 * @param facilityName 시설명
	 * @param postcode 우편번호
	 * @param address 주소 
	 * @param addressDetail 상세주소
	 * @param phoneNumber 전화번호
	 * @throws DuplicateException  중복 예외
	 */
	public void addCenter(String centerName, String facilityName, String postcode, String address, String addressDetail, String phoneNumber) throws DuplicateException {
		CenterList dto = new CenterList(centerName, facilityName, postcode, address, addressDetail, phoneNumber);

		addCenter(dto);
	} 	

	/**
	 * 전체조회 - 관리자 전용 
	 * @return 전체 목록
	 */
	public ArrayList<CenterList> getList() {
		return list;
	}

	/**
	 * 현재 등록 센터 수 조회
	 * @return 등록센터 수
	 */
	public int getCount() {
		return list.size();
	}

	/**
	 * 센터명으로 조회
	 * @param centerName
	 * @return 존재하면 센터 정보, 없으면 데이터검색 예외
	 * @throws RecordNotFoundException 데이터 검색 예외
	 */
	public CenterList getListCenterName(String centerName) throws RecordNotFoundException {
		int index = existCenterName(centerName);
		if (index >= 0) {
			return (CenterList)list.get(index);
		}
		throw new RecordNotFoundException(centerName);
	}

	/**
	 * 시설명으로 조회
	 * @param facilityName
	 * @return 존재하면 센터 정보, 없으면 데이터검색 예외
	 * @throws RecordNotFoundException 데이터 검색 예외
	 */
	public CenterList getListFacility(String facilityName) throws RecordNotFoundException {
		int index = existFacilityName(facilityName);
		if (index >= 0) {
			return (CenterList)list.get(index);
		}
		throw new RecordNotFoundException(facilityName);
	}

	/**
	 * 센터 주소(시 군 구)로 조회
	 * @param address
	 * @throws RecordNotFoundException 데이터 검색 예외
	 */
	public void getListAddress(String address)  throws RecordNotFoundException{
		existAddressList(address);
		if(sameRegionList.size() > 0) {
			for (int index = 0; index < sameRegionList.size(); index++) {
				int indexRegion = (int)existAddressList(address).get(index);
				System.out.println(list.get(indexRegion));
			}
		} else {
			throw new RecordNotFoundException(address);
		}
		
	}

	/**
	 * 전화번호로 센터 조회 
	 * @param phoneNumber 
	 * @return 존재하면 센터 정보, 없으면 데이터검색 예외
	 * @throws RecordNotFoundException 데이터 검색 예외
	 */
	public CenterList getListPhone(String phoneNumber) throws RecordNotFoundException{
		int index = existPhoneNumber(phoneNumber);
		if (index >= 0) {
			return (CenterList)list.get(index);
		}
		throw new RecordNotFoundException(phoneNumber);
	}


	/**
	 * 센터 리스트 전체 삭제 
	 * @return 리스트 크기 
	 */
	public int removeCenterListAll() {
		list.clear();
		return list.size();
	}

	/**
	 * 센터 리스트 중 입력한 하나 삭제 
	 * @param centerName 센터 이름
	 * @return 존재하면 삭제, 없으면 에러 
	 * @throws RecordNotFoundException 데이터 검색 예외
	 */
	public CenterList removeCenterList(String centerName) throws RecordNotFoundException{
		int index = existCenterName(centerName);
		if (index >= 0) {
			return list.remove(index);
		}
		throw new RecordNotFoundException(centerName);
	}

	/**
	 * 센터 이름 기준 센터 정보 변경 
	 * @param dto 변경할 정보 
	 * @return 성공시 true, 실패시 false
	 * @throws RecordNotFoundException 데이터 검색 예외
	 */
	public boolean setCenterImfo(CenterList dto) throws RecordNotFoundException {
		int index = existCenterName(dto.getCenterName());
		if (index >= 0) {
			list.set(index, dto);
			return true;
		}
		throw new RecordNotFoundException(dto.getCenterName());
	}

}
