package com.work.service;

import java.util.ArrayList;

import com.work.exception.DuplicateException;
import com.work.exception.RecordNotFoundException;
import com.work.model.dto.ReserveMember;

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
	 * @param dto
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

		// 자동계산으로 하도록 메서드 추가
		//dto.secondVaccDay() 
		//dto.callDay()
		addMember(dto);
	} 	

	/**
	 * 전체조회 - 관리자 전용
	 * @return 전체 목록
	 */
	public ArrayList<ReserveMember> getList() {
		return list;
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
	 * 2차 접종일 계산
	 */

	

	/**
	 * 등록된 정보 수정 
	 */


}
