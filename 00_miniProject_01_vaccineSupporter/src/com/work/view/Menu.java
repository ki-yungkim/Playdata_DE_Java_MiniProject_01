package com.work.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.sun.jndi.cosnaming.IiopUrl.Address;
import com.work.exception.DuplicateException;
import com.work.exception.RecordNotFoundException;
import com.work.model.dto.CenterList;
import com.work.model.dto.VaccineCount;
import com.work.service.CenterService;
import com.work.service.ReserveNotifyService;
import com.work.service.VaccineCountService;

/**
 * <pre>
 * 회원관리시스템 메뉴 구성
 * </pre>
 *  
 * @author 김기영
 * @version ver.1.0
 * @since jdk1.8
 */
public class Menu {
	/** 회원관리 서비스 클래스 */
	public CenterService service = new CenterService();
	public ReserveNotifyService notifyService = new ReserveNotifyService();
	public VaccineCountService vacciService = new VaccineCountService();
	
	public void mainMenu() {
		printTitle("백신 도우미 메인메뉴");
		System.out.println("1. 어제 접종자 조회");
		System.out.println("2. 누적 접종자 조회");
		System.out.println("3. 우선 접종대상자 조회");
		System.out.println("4. 예방접종센터 조회");
		System.out.println("5. 백신별 2차 접종 대기기간 조회");
		System.out.println("6. 백신 알림기능 신청자 정보 수정");
		System.out.println("9. 관리자 메뉴");
		System.out.println("0. 프로그램 종료");
		printLine();
		
		System.out.print("메뉴번호 입력 : ");
		int menuNo = inputNumber();
		
		switch(menuNo) {
		case 1:
			System.out.println("1. 어제 접종자 조회");
			getYesterdayVacciCountMenu();
			break;
		case 2:
			System.out.println("2. 누적 접종자 조회");
			getTotalVacciCountMenu();
			break;
		case 3:
			System.out.println("3. 우선 접종대상자 조회");
			prefferdMemberMenu();
			break;
		case 4:
			System.out.println("4. 예방접종센터 조회");
			searchCenterMenu();
			break;
		case 5:
			System.out.println("5. 백신별 2차 접종 대기기간 조회");
			waitVaccine();
			break;
		case 6:
			System.out.println("6. 백신 알림기능 신청자 정보 수정");
			break;
		case 9:
			System.out.println("99. 관리자 메뉴");
			adminMainMenu();
			break;
		case 0:
			exitMenu();
			break;
		default:
			System.out.println("메뉴번호 오류");
			break;
		}
		
	}
	
	/**
	 * 프로그램 서비스전에 초기화 작업 위한 메뉴 
	 */
	public void initMenu() {
		// 초기화 센터 목록 등록 
		int count = 0;
		try {
			count = service.initCenter();
			vacciService.initCount();
			
		} catch (DuplicateException e) {
			print("[실패] " + e.getMessage());		
		}
		print("[회원 초기화 작업이 완료되었습니다. 현재 등록 회원수 (" + count + ")명]");
		
		// 프로그램 서비스전에 기존 회원정보 파일가져와서 회원관리저장구조 메모리에 저장하기
		// loadMemberDataFile();

	}
	
	
	/** 프로그램 종료 메뉴 */
	public void exitMenu() {
		// 프로그램 종료전에 회원정보 파일 저장
		// saveMemberDataFile();
		
		printTitle("회원관리 프로그램 정상 종료");
		System.exit(0);
	}

	
	
	/**
	 * 메세지 출력
	 * @param message 메세지
	 */
	public void print(String message) {
		System.out.println(message);
	}
	
	
	private void getYesterdayVacciCountMenu() {
		printTitle("어제 접종자 조회 메뉴");
			
		System.out.print("지역 : ");
		String region = inputString();
		
		try {
			int resultFirst = vacciService.getRegionCountFirst(region);
			int resultSecond = vacciService.getRegionCountSecond(region);
			print("어제 "+ region + " 지역 1차 접종자 수는 " + resultFirst + "명입니다." );
			print("어제 "+ region + " 지역 2차 접종자 수는 " + resultSecond + "명입니다." );
		} catch (RecordNotFoundException e) {
			print("잘못된 형식을 입력하셨습니다.");
			mainMenu();
		}
	}
	
	
	private void getTotalVacciCountMenu() {
		printTitle("누적 접종자 조회 메뉴");
			
		System.out.print("지역 : ");
		String region = inputString();
		
		try {
			int resultFirst = vacciService.getRegionCountTotalFirst(region);
			int resultSecond = vacciService.getRegionCountTotalSecond(region);
			print(region + " 지역 누적 1차 접종자 수는 " + resultFirst + "명입니다." );
			print(region + " 지역 누적 2차 접종자 수는 " + resultSecond + "명입니다." );
		} catch (RecordNotFoundException e) {
			print("잘못된 형식을 입력하셨습니다.");
			mainMenu();
		}
	}
	
	private void prefferdMemberMenu() {
		printTitle("우선접종대상자 조회 메뉴");
			
		System.out.print("나이 : ");
		int age = inputNumber();
		System.out.print("직군 : ");
		String job = inputString();
		
		if (age >= 50) {
			switch(job) {
			case "의료" :
				System.out.println("우선접종대상자입니다.");
				break;
			case "경찰" :
				System.out.println("우선접종대상자입니다.");
				break;
			case "소방" :
				System.out.println("우선접종대상자입니다.");
				break;
			case "군인" : 
				System.out.println("우선접종대상자입니다.");
				break;
			default :
				System.out.println("우선접종대상자가 아닙니다.");
			}
		} else {
			System.out.println("우선접종대상자가 아닙니다.");
		}
	}
	
	private void searchCenterMenu() {
		printTitle("예방접종센터 조회 메뉴");
			
		System.out.println("1. 센터명으로 찾기");
		System.out.println("2. 시설명으로 찾기");
		System.out.println("3. 주소로 찾기");
		System.out.println("4. 전화번호로 찾기");
		System.out.println("0. 메인메뉴로 돌아가기");
		printLine();
		
		System.out.print("메뉴번호 입력 : ");
		
		int menuNo = inputNumber();
		
		switch(menuNo) {
		case 1:
			searchListCenterNameMenu();
			break;
		case 2:
			searchListFacilityNameMenu();
			break;
		case 3:
			searchListAddressMenu();
			break;
		case 4:
			searchListPhoneNumberMenu();
			break;	
		case 0:
			mainMenu();
			break;
		default:
			System.out.println("메뉴번호 오류");
			break;
		}
	}
	
	private void searchListCenterNameMenu() {
		printTitle("센터명으로 찾기 메뉴");
		System.out.print("센터명 : ");
		String centerName = inputString();
		
		try {
			CenterList result = service.getListCenterName(centerName);
			System.out.println(result);
		} catch (RecordNotFoundException e) {
			print("잘못된 형식을 입력하셨습니다.");
			mainMenu();
		}
		
	}
	
	private void searchListFacilityNameMenu() {
		printTitle("시설명으로 찾기 메뉴");
		System.out.print("시설명 : ");
		String facilityName = inputString();
		
		try {
			CenterList result = service.getListFacility(facilityName);
			System.out.println(result);
		} catch (RecordNotFoundException e) {
			print("잘못된 형식을 입력하셨습니다.");
			mainMenu();
		}
	}
	
	private void searchListAddressMenu() {
		printTitle("주소로 찾기 메뉴");
		System.out.print("주소 [시, 군, 구 단위]: ");
		String adress = inputString();
		
		try {
			CenterList result = service.getListAddress(adress);
			System.out.println(result);
		} catch (RecordNotFoundException e) {
			print("잘못된 형식을 입력하셨습니다.");
			mainMenu();
		}
	}
	
	private void searchListPhoneNumberMenu() {
		printTitle("전화번호로 찾기 메뉴");
		System.out.print("전화번호 [012-1234-1234] : ");
		String phoneNumber = inputString();
		
		try {
			CenterList result = service.getListPhone(phoneNumber);
			System.out.println(result);
		} catch (RecordNotFoundException e) {
			print("잘못된 형식을 입력하셨습니다.");
			mainMenu();
		}
	}
	
	private void waitVaccine() {
		printTitle("백신별 2차 접종 대기기간 조회");
			
		System.out.print("백신명 [화이자, 모더나, 아스트라제네카] : ");
		String vaccineName = inputString();
		
			switch(vaccineName) {
			case "화이자" :
				System.out.println("화이자 백신은 1차 접종 후 3주 후 2차 접종입니다.");
				break;
			case "모더나" :
				System.out.println("모더나 백신은 1차 접종 후 4주 후 2차 접종입니다.");
				break;
			case "아스트라제네카" :
				System.out.println("아스트라제네카 백신은 1차 접종 후 12주 후 2차 접종입니다.");
				break;
			
			default :
				System.out.println("백신 이름을 정확하게 입력해주십시오.");
			}
		
		}
	

	/**
	 * 백신 알림기능 신청자 정보 수정 서비스 메인메뉴
	 */
	private void serviceMainMenu() {
		printTitle("백신 알림기능 신청자 정보 수정");
		
		System.out.println("1. 내정보조회");
		System.out.println("2. 내정보변경");
		System.out.println("9. 로그아웃");
		System.out.println("0. 프로그램 종료");
		printLine();
		System.out.print("메뉴번호 입력 : ");
		
		int menuNo = inputNumber();
		
		switch(menuNo) {
		case 1:
			break;
		case 2:
			break;
		case 3:
			break;
		case 9:
			print("[로그아웃 성공] 서비스를 이용하시려면 로그인하시기 바랍니다.");
			mainMenu();
			break;
		case 0:
			exitMenu();
			break;
		default:
			System.out.println("메뉴번호 오류");
			break;
		}
	}

	private void adminMainMenu() {
		printTitle("관리자 서비스 메인메뉴");
		
		System.out.println("1. 접종자 수 등록");
		System.out.println("2. 센터 정보 변경");
		System.out.println("3. 백신알림기능 신청자 정보 삭제");
		System.out.println("9. 로그아웃");
		System.out.println("0. 프로그램 종료");
		printLine();
		System.out.print("메뉴번호 입력 : ");
		
		int menuNo = inputNumber();
		
		switch(menuNo) {
		case 1:
			addVacciCountMenu();
			break;
		case 2:
			setCenterInfoMenu();
			break;
		case 3:
			
			break;
		case 9:
			mainMenu();
			break;
		case 0:
			exitMenu();
			break;
		default:
			System.out.println("메뉴번호 오류");
			break;
		}	}

	private void addVacciCountMenu() {
		printTitle("접종자 수 등록");
		
		vacciService.removeCountAll();
		
		System.out.print("날짜 : ");
		String day = inputString();
		System.out.print("지역 : ");
		String region = inputString();
		System.out.print("어제 1차 접종자 수 : ");
		int yesterdayFirst = inputNumber();
		System.out.print("어제 2차 접종자 수 : ");
		int yesterdaySecond = inputNumber();
		System.out.print("누적 1차 접종자 수 : ");
		int totalFirst = inputNumber();
		System.out.print("누적 2차 접종자 수 : ");
		int totalSecond = inputNumber();
		
		
		VaccineCount dto = new VaccineCount(day, region, yesterdayFirst, yesterdaySecond, totalFirst, totalSecond );
		try {
			vacciService.addVacciCount(day, region, yesterdayFirst, yesterdaySecond, totalFirst, totalSecond);
			print("[등록 성공] " + "일자 : " + day + ", 지역 : " + region);
		} catch (DuplicateException e) {
			print("[등록 실패]" + e.getMessage());
			mainMenu();
		}
		
	}
		
	private void setCenterInfoMenu() {
		printTitle("센터 정보 변경 메뉴");
		System.out.print("센터명 : ");
		String centerName = inputString();
		
		System.out.print("시설명 : ");
		String facilityName = inputString();
		
		System.out.print("우편번호 : ");
		String postcode = inputString();
		
		System.out.print("주소 : ");
		String address = inputString();
		
		System.out.print("상세주소 : ");
		String addressDetail = inputString();
		
		System.out.print("전화번호 : ");
		String phoneNumber = inputString();
		
		
		CenterList dto = new CenterList(centerName, facilityName, postcode, address, addressDetail, phoneNumber);
		try {
			service.setCenterImfo(dto);
			print("[센터 정보 변경 성공]");
		} catch (RecordNotFoundException e) {
			// TODO Auto-generated catch block
			print("[변경 실패]" + e.getMessage());
			mainMenu();
		}
		
	}
	
	
	/**
	 * 구분선 출력
	 */
	public void printLine() {
		System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
	}
	
	/**
	 * 제목 출력
	 * @param title 제목
	 */
	public void printTitle(String title) {
		System.out.println();
		printLine();
		System.out.println(title);
		printLine();
	}
	
	/**
	 * 문자열 입력 반환
	 * @return 입력 문자열
	 */
	public String inputString() {
		String data = null;
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			data = in.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return data;
	}
	
	/**
	 * 숫자 입력 반환
	 * @return 입력 정수형 숫자
	 */
	public int inputNumber() {
		String data = null;
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			data = in.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Integer.parseInt(data);
	}
	
}
