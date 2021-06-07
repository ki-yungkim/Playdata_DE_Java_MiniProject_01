package com.work.service;

import java.util.ArrayList;

import com.work.exception.CommonException;
import com.work.exception.DuplicateException;
import com.work.exception.RecordNotFoundException;
import com.work.model.dto.ReserveMember;

/**
 * <pre>
 * ReserveMember 구현 서비스 클래스
 * </pre>
 * @author 김기영
 * @version ver 1.0
 * @since jdk1.8
 *
 */
public class ReserveNotifyService {

	public ArrayList<ReserveMember> list = new ArrayList<ReserveMember>();

	/** 기본생성자 */
	public ReserveNotifyService() {}

	/**
	 * 예약자 리스트에 이름 존재 유무 조회
	 * @param name 이름
	 * @return 존재하면 index 번호, 없으면 -1
	 */
	public int existName(String name) {
		for (int index = 0; index < list.size(); index++) {
			if (list.get(index).getName().equals(name)) {
				return index;
			}
		}
		return -1;
	}

	/**
	 * 예약자 리스트에 주민번호 존재 유무 조회
	 * @param idNumber 주민번호
	 * @return 존재하면 index 번호, 없으면 -1
	 */
	public int existIdNumber(String idNumber) {
		for (int index = 0; index < list.size(); index++) {
			if (list.get(index).getIdNumber().equals(idNumber)) {
				return index;
			}
		}
		return -1;
	}

	/**
	 * 예약자 멤버 등록
	 * @param dto 예약자 멤버
	 * @throws DuplicateException
	 */
	public void addMember(ReserveMember dto) throws DuplicateException  {
		int index = existName(dto.getName());
		if (index >= 0) {
			throw new DuplicateException(dto.getName());
		}

		list.add(dto);
	}

	/**
	 * 예약자 초기 등록
	 * @return 리스트 크기
	 * @throws DuplicateException
	 */
	public int initReserve() throws DuplicateException {
		ReserveMember dto1 = new ReserveMember("홍길동", "010-1234-1000", "대전광역시", "900101-1100000", "화이자", "210601");
		ReserveMember dto2 = new ReserveMember("김일영", "010-1234-2000", "서울특별시", "900201-2200000", "모더나", "210602");
		ReserveMember dto3 = new ReserveMember("김이영", "010-1234-3000", "부산광역시", "800301-1300000", "아스트로제네카", "210603");
		ReserveMember dto4 = new ReserveMember("김삼영", "010-1234-4000", "인천광역시", "900401-1400000", "화이자", "210604");
		ReserveMember dto5 = new ReserveMember("김사영", "010-1234-5000", "울산광역시", "700501-1500000", "모더나", "210605");
		
		addMember(dto1);	
		addMember(dto2);	
		addMember(dto3);	
		addMember(dto4);	
		addMember(dto5);	
		
		return list.size();
	}
	
	/**
	 * 예약자 추가 등록
	 * @param name 이름
	 * @param phoneNumber 전화번호
	 * @param address 주소
	 * @param idNumber 주민번호 
	 * @param vaccine 백신종류
	 * @param firstVaccination 1차 접종일
	 * @throws DuplicateException 
	 */
	public void addMember(String name, String phoneNumber, String address, String idNumber, String vaccine, String firstVaccination) throws DuplicateException {
		ReserveMember dto = new ReserveMember(name, phoneNumber, address, idNumber, vaccine, firstVaccination);
		
		addMember(dto);
	} 	

	/**
	 * 전체조회 - 관리자 전용
	 * @return 전체 목록
	 */
	public ArrayList<ReserveMember> getList() {
		for (int index = 0; index < list.size() ; index++) {
			System.out.println(list.get(index));
		}
		return null;
		 
	}

	/**
	 * 이름으로 조회 - 관리자용 
	 * @param name 이름
	 * @return 존재하면 정보, 없으면 데이터검색 예외
	 * @throws RecordNotFoundException
	 */
	public ReserveMember getListMember(String name) throws RecordNotFoundException {
		int index = existName(name);
		if (index >= 0) {
			return (ReserveMember)list.get(index);
		}
		throw new RecordNotFoundException(name);

	}

	/**
	 * 이름, 주민번호로 조회
	 * @param name 이름
	 * @param idNumber 주민번호
	 * @return 존재하면 정보, 없으면 데이터검색 예외
	 * @throws RecordNotFoundException
	 */
	public ReserveMember getListMember(String name, String idNumber) throws RecordNotFoundException {
		int indexName = existName(name);
		int indexId = existIdNumber(idNumber);
		if (indexName >= 0) {
			if (indexName == indexId) {
				return (ReserveMember)list.get(indexName);
			}

		}
		throw new RecordNotFoundException(name);

	}

	/**
	 * 예약자 정보 전체 삭제 - 관리자용 
	 * @return 리스트 크기
	 */
	public int removeMemberListAll() {
		list.clear();
		return list.size();
	}

	/**
	 * 예약자 정보 1개 삭제 - 관리자용
	 * @param name 이름
	 * @return 존재하면 삭제, 없으면 에러 
	 * @throws RecordNotFoundException
	 */
	public ReserveMember removeMemberList(String name) throws RecordNotFoundException {
		int index = existName(name);
		if (index >= 0) {
			return list.remove(index);
		}

		throw new RecordNotFoundException(name);
	}

	/**
	 * 예약자 정보 1개 삭제
	 * @param name 이름
	 * @param idNumber 주민번호
	 * @return 존재하면 삭제, 없으면 에러 
	 * @throws RecordNotFoundException
	 */
	public ReserveMember removeMemberList(String name, String idNumber) throws RecordNotFoundException {
		int indexName = existName(name);
		int indexId = existIdNumber(idNumber);
		if (indexName >= 0) {
			if (indexName == indexId) {
				return list.remove(indexName);
			}
		}
		throw new RecordNotFoundException(name);
	}
	
	/**
	 * 이름 기준 정보 변경
	 * @param dto 변경할 정보
	 * @return 성공시 true, 실패시 false
	 * @throws RecordNotFoundException
	 */
	public boolean setMemberImfo(ReserveMember dto) throws RecordNotFoundException {
		int index = existName(dto.getName());
		if (index >= 0) {
			list.set(index, dto);
			return true;
		}
		throw new RecordNotFoundException(dto.getName());
	}
	
	/**
	 * 이름, 주민번호으로 확인, 변경할 정보로 정보 변경
	 * @param name 이름
	 * @param idNumber 주민번호
	 * @param dto 변경할 정보 
	 * @return 성공시 true, 실패시 false
	 * @throws RecordNotFoundException
	 */
	public boolean setMemberImfo(String name, String idNumber, ReserveMember dto) throws RecordNotFoundException {
		int indexName = existName(name);
		int indexId = existIdNumber(idNumber);
		int indexDto = existName(dto.getName());
		if (indexName >= 0) {
			if (indexName == indexId && indexName == indexDto) {
				list.set(indexName, dto);
				return true;
			}
		}
		throw new RecordNotFoundException(name);
	}
	
	
	/**
	 * 이름, 주민번호로 로그인
	 * @param name 이름
	 * @param idNumber 주민번호
	 * @return 정보가 있으면 true 
	 * @throws RecordNotFoundException
	 * @throws CommonException
	 */
	public boolean login(String name, String idNumber) throws RecordNotFoundException, CommonException {
		try {
			ReserveMember dto = getListMember(name);
			if (dto.getIdNumber().equals(idNumber)) {
				return true;
			} 
			throw new CommonException("회원의 정보가 올바르지 않습니다.");
		} catch (RecordNotFoundException e) {
			throw e;
		}
	}
	
	/**
	 * 관리자 로그인
	 * @param id 관리자 아이디 admin00
	 * @param pw 관리자 비밀번호 password00
	 * @return 정보 맞으면 true
	 * @throws RecordNotFoundException
	 * @throws CommonException
	 */
	public boolean adminLogin(String id, String pw) throws RecordNotFoundException, CommonException {
		String adminId = "admin00";
		String adminPw = "password00";
		if (id.equals(adminId) &&  pw.equals(adminPw)) {
			return true;
		} 
		throw new CommonException("회원의 정보가 올바르지 않습니다.");
	}
	
	
}
