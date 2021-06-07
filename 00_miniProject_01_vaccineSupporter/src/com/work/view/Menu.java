package com.work.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.work.exception.CommonException;
import com.work.exception.DuplicateException;
import com.work.exception.RecordNotFoundException;
import com.work.model.dto.CenterList;
import com.work.model.dto.ReserveMember;
import com.work.model.dto.VaccineCount;
import com.work.service.CenterService;
import com.work.service.ReserveNotifyService;
import com.work.service.VaccineCountService;
import com.work.util.Utility;

/**
 * <pre>
 * 백신 도우미 시스템 메뉴 구성
 * </pre>
 *  
 * @author 김기영
 * @version ver.1.0
 * @since jdk1.8
 */
public class Menu {
	
	/** 서비스 클래스와 유틸리티 */
	public CenterService service = new CenterService();
	public ReserveNotifyService notifyService = new ReserveNotifyService();
	public VaccineCountService vacciService = new VaccineCountService();
	public Utility utility = new Utility();

	/**
	 * 백신 도우미 메인메뉴
	 * @throws IOException 
	 * @throws NumberFormatException
	 */
	public void mainMenu() throws IOException, NumberFormatException {
		printTitle("백신 도우미 메인메뉴");
		System.out.println("1. 접종자 조회"); 
		System.out.println("2. 누적 접종자 조회");
		System.out.println("3. 우선 접종대상자 조회");
		System.out.println("4. 예방접종센터 조회");
		System.out.println("5. 백신별 2차 접종 대기기간 조회");
		System.out.println("6. 백신 알림기능 신청자 정보 관리");
		System.out.println("9. 관리자 메뉴");
		System.out.println("0. 프로그램 종료");
		printLine();
		System.out.println();
		System.out.print("메뉴번호 입력 : ");
		int menuNo = inputNumber();

		switch(menuNo) {
		case 1:
			getYesterdayVacciCountMenu();
			break;
		case 2:
			getTotalVacciCountMenu();
			break;
		case 3:
			prefferdMemberMenu();
			break;
		case 4:
			searchCenterMenu();
			break;
		case 5:
			try {
				waitVaccine();
			} catch (Exception e) {
				System.out.println("[오류] 맞지 않는 형식입니다.");
			}
			break;
		case 6:
			try {
				login();
			} catch (RecordNotFoundException e) {
				System.out.println("RecordNotFoundException");
			} catch (CommonException e) {
				System.out.println("CommonException");
			}
			break;
		case 9:
			try {
				adminLogin();
			} catch (RecordNotFoundException e) {
				System.out.println("RecordNotFoundException");
			} catch (CommonException e) {
				System.out.println("CommonException");
			}
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
	 * 프로그램 서비스 전 초기화 작업 메뉴 
	 */
	public void initMenu() {
		int count = 0;
		try {
			count = service.initCenter();
			vacciService.initCount();
			notifyService.initReserve();

		} catch (DuplicateException e) {
			print("[실패] " + e.getMessage());		
		}
		print("[초기화 작업이 완료되었습니다.]");
	}

	/** 프로그램 종료 메뉴 */
	public void exitMenu() {

		printTitle("백신 도우미 프로그램 정상 종료");
		System.exit(0);
	}


	/**
	 * 메세지 출력
	 * @param message 메세지
	 */
	public void print(String message) {
		System.out.println(message);
	}

	/**
	 * 접종자 조회 메뉴
	 * @throws IOException
	 */
	private void getYesterdayVacciCountMenu() throws IOException {
		printTitle("접종자 조회 메뉴");

		System.out.println("지역 목록 : ");
		System.out.println("[전국, 서울특별시, 부산광역시, 대구광역시, 인천광역시, 대전광역시, 광주광역시, 울산광역시, 세종특별자치시, 강원도]");
		System.out.println();
		
		System.out.print("날짜 [yy-MM-dd] : ");
		String day = inputString();

		System.out.print("지역 : ");
		String region = inputString();

		try {
			int resultFirst = vacciService.getRegionCountFirst(day, region);
			int resultSecond = vacciService.getRegionCountSecond(day, region);
			System.out.println();
			print(day + " 날짜의 " +  region + " 지역 1차 접종자 수는 " + utility.commaThousand(resultFirst) + "명입니다." );
			print(day + " 날짜의 " + region + " 지역 2차 접종자 수는 " + utility.commaThousand(resultSecond) + "명입니다." );
			pause();
		} catch (RecordNotFoundException e) {
			print("잘못된 형식을 입력하셨습니다.");
			pause();
		}
	}

	/**
	 * 누적 접종자 조회 메뉴
	 * @throws IOException
	 */
	private void getTotalVacciCountMenu() throws IOException {
		printTitle("누적 접종자 조회 메뉴");
		System.out.println("지역 목록 : ");
		System.out.println("[전국, 서울특별시, 부산광역시, 대구광역시, 인천광역시, 대전광역시, 광주광역시, 울산광역시, 세종특별자치시, 강원도]");
		System.out.println();
		System.out.print("날짜 [yy-MM-dd] : ");
		String day = inputString();

		System.out.print("지역 : ");
		String region = inputString();

		try {
			int resultFirst = vacciService.getRegionCountTotalFirst(day, region);
			int resultSecond = vacciService.getRegionCountTotalSecond(day, region);
			System.out.println();
			print(day + " 날짜의 " + region + " 지역 누적 1차 접종자 수는 " + utility.commaThousand(resultFirst) + "명입니다." );
			print(day + " 날짜의 " + region + " 지역 누적 2차 접종자 수는 " + utility.commaThousand(resultSecond) + "명입니다." );
			pause();
		} catch (RecordNotFoundException e) {
			print("잘못된 형식을 입력하셨습니다.");
			mainMenu();
			pause();
		}
	}

	/**
	 * 우선접종 대상자 조회 메뉴
	 * @throws IOException
	 */
	private void prefferdMemberMenu() throws IOException {
		printTitle("우선접종대상자 조회 메뉴");

		System.out.print("나이 : ");
		int age = inputNumber();
		System.out.println("직군 목록 : [회사원, 학생, 의료, 경찰, 소방, 군인]");
		System.out.print("직군 : ");
		String job = inputString();

		if (age >= 50) {
			System.out.println("우선접종대상자입니다.");
			System.out.print("근처 센터를 조회하시겠습니까? [O, X] ");
			String ox = inputString();
			if (ox.equals("O")) {
				searchCenterMenu();
			}

		} else {
			switch(job) {
			case "의료" :
				System.out.println("우선접종대상자입니다.");
				System.out.print("근처 센터를 조회하시겠습니까? [O, X] ");
				String ox1 = inputString();
				if (ox1.equals("O")) {
					searchCenterMenu();
				}
				break;
			case "경찰" :
				System.out.println("우선접종대상자입니다.");
				System.out.print("근처 센터를 조회하시겠습니까? [O, X] ");
				String ox2 = inputString();
				if (ox2.equals("O")) {
					searchCenterMenu();
				}
				break;
			case "소방" :
				System.out.println("우선접종대상자입니다.");
				System.out.print("근처 센터를 조회하시겠습니까? [O, X] ");
				String ox3 = inputString();
				if (ox3.equals("O")) {
					searchCenterMenu();
				}
				break;
			case "군인" : 
				System.out.println("우선접종대상자입니다.");
				System.out.print("근처 센터를 조회하시겠습니까? [O, X] ");
				String ox4 = inputString();
				if (ox4.equals("O")) {
					searchCenterMenu();
				}
				break;
			default :
				System.out.println("우선접종대상자가 아닙니다.");
				pause();
			}
		}
	}

	/**
	 * 예방접종센터 조회 메뉴
	 * @throws IOException
	 */
	private void searchCenterMenu() throws IOException {
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
			try {
				searchListAddressMenu();
			} catch (RecordNotFoundException e) {
				e.printStackTrace();
			}
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

	/**
	 * 센터명으로 센터 정보 조회 메뉴
	 * @throws IOException
	 */
	private void searchListCenterNameMenu() throws IOException {
		printTitle("센터명으로 찾기 메뉴");
		System.out.print("센터명 : ");
		String centerName = inputString();

		try {
			CenterList result = service.getListCenterName(centerName);
			System.out.println("센터명, 시설명, 우편번호, 주소, 상세주소 사무실전화번호");
			System.out.println(result);
			pause();
			searchCenterMenu();
		} catch (RecordNotFoundException e) {
			print("잘못된 형식을 입력하셨습니다.");
			pause();
			searchCenterMenu();
		}

	}

	/**
	 * 시설명으로 센터 정보 조회 메뉴
	 * @throws IOException
	 */
	private void searchListFacilityNameMenu() throws IOException {
		printTitle("시설명으로 찾기 메뉴");
		System.out.print("시설명 : ");
		String facilityName = inputString();

		try {
			CenterList result = service.getListFacility(facilityName);
			System.out.println("센터명, 시설명, 우편번호, 주소, 상세주소 사무실전화번호");
			System.out.println(result);
			pause();
			searchCenterMenu();
		} catch (RecordNotFoundException e) {
			print("잘못된 형식을 입력하셨습니다.");
			pause();
			searchCenterMenu();
		}
	}

	/**
	 * 주소로 센터 정보 조회 메뉴
	 * @throws RecordNotFoundException
	 * @throws IOException 
	 * @throws NumberFormatException 
	 */
	private void searchListAddressMenu() throws RecordNotFoundException, NumberFormatException, IOException  {
		printTitle("주소로 찾기 메뉴");
		System.out.print("주소 [시, 군, 구 단위]: ");
		String adress = inputString();

		try {
			System.out.println("센터명, 시설명, 우편번호, 주소, 상세주소 사무실전화번호");
			service.getListAddress(adress);
			pause();
			searchCenterMenu();
		} catch (RecordNotFoundException e) {
			print("잘못된 형식을 입력하셨습니다.");
			pause();
			searchCenterMenu();
		}
		
	}
	
	/**
	 * 전화번호로 센터 정보 조회 메뉴
	 * @throws IOException
	 */
	private void searchListPhoneNumberMenu() throws IOException {
		printTitle("전화번호로 찾기 메뉴");
		System.out.print("전화번호 [012-1234-1234] : ");
		String phoneNumber = inputString();

		try {
			CenterList result = service.getListPhone(phoneNumber);
			System.out.println("센터명, 시설명, 우편번호, 주소, 상세주소 사무실전화번호");
			System.out.println(result);
			pause();
			searchCenterMenu();
		} catch (RecordNotFoundException e) {
			print("잘못된 형식을 입력하셨습니다.");
			pause();
			searchCenterMenu();
		}
	}

	/**
	 * 백신별 2차 접종 대기기간 조회 메뉴
	 * @throws Exception
	 */
	private void waitVaccine() throws Exception {
		printTitle("백신별 2차 접종 대기기간 조회");

		System.out.print("백신명 [화이자, 모더나, 아스트라제네카] : ");
		String vaccineName = inputString();

		System.out.print("접종일 [yy-MM-dd] : ");
		String firstDay = inputString();

		String result = null;
		switch(vaccineName) {
		case "화이자" :
			System.out.println("화이자 백신은 1차 접종 3주 후 2차 접종입니다.");
			result = utility.addDate(vaccineName, firstDay);
			System.out.println("예정일 : " + result);
			System.out.print("2차 접종일 알림을 받으시겠습니까? [O, X] ");
			String ox1 = inputString();
			if (ox1.equals("O")) {
				setMemberMenu();
			}
			break;
		case "모더나" :
			System.out.println("모더나 백신은 1차 접종 4주 후 2차 접종입니다.");
			result = utility.addDate(vaccineName, firstDay);
			System.out.println("예정일 : " + result);
			System.out.print("2차 접종일 알림을 받으시겠습니까? [O, X] ");
			String ox2 = inputString();
			if (ox2.equals("O")) {
				setMemberMenu();
			}
			break;
		case "아스트라제네카" :
			System.out.println("아스트라제네카 백신은 1차 접종 12주 후 2차 접종입니다.");
			result = utility.addDate(vaccineName, firstDay);
			System.out.println("예정일 : " + result);
			System.out.print("2차 접종일 알림을 받으시겠습니까? [O, X] ");
			String ox3 = inputString();
			if (ox3.equals("O")) {
				setMemberMenu();
			}
			break;

		default :
			System.out.println("백신 이름과 1차 접종일을 정확하게 입력해주십시오.");
		}

	}


	/** 백신 알림기능 신청자 등록 메뉴 */
	private void setMemberMenu() {
		printTitle("백신 알림기능 신청자 등록 메뉴");

		System.out.print("이름 : ");
		String name = inputString();

		System.out.print("전화번호 : ");
		String phoneNumber = inputString();

		System.out.print("주소 : ");
		String address = inputString();

		System.out.print("주민번호 : ");
		String idNumber = inputString();

		System.out.print("백신종류 : ");
		String vaccineName = inputString();

		System.out.print("1차 접종일 : ");
		String firstDay = inputString();

		ReserveMember dto = new ReserveMember(name, phoneNumber, address, idNumber,vaccineName, firstDay);

		try {
			notifyService.addMember(name, phoneNumber, address, idNumber, vaccineName, firstDay);
			print("[등록 성공] " + name + "님의 2차 접종일 알림 문자 연락이 갈 예정입니다.");
			pause();
		} catch (DuplicateException e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * 2차 접종일 알림 등록자 로그인 메뉴
	 * @throws RecordNotFoundException
	 * @throws CommonException
	 * @throws IOException
	 */
	private void login() throws RecordNotFoundException, CommonException, IOException {
		printTitle("로그인 메뉴");

		System.out.print("이름 : ");
		String name = inputString();

		System.out.print("주민번호 : ");
		String idNumber = inputString();

		if(notifyService.login(name, idNumber)) {
			secondVaccineImfoMenu();
		} else {
			mainMenu();
		}

	}

	/**
	 * 백신 알림기능 신청자 정보 수정 서비스 메인메뉴
	 * @throws IOException 
	 */
	private void secondVaccineImfoMenu() throws IOException {
		printTitle("백신 알림기능 신청자 정보 관리 메뉴");
		System.out.println("1. 내정보조회");
		System.out.println("2. 내정보변경");
		System.out.println("9. 로그아웃");
		System.out.println("0. 프로그램 종료");
		printLine();
		System.out.print("메뉴번호 입력 : ");

		int menuNo = inputNumber();

		switch(menuNo) {
		case 1:
			getInfo();
			break;
		case 2:
			setInfo();
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


	/**
	 * 2차 접종 알림 등록자 정보 조회 메뉴 
	 * @throws IOException
	 */
	private void getInfo() throws IOException {
		printTitle("내 정보 조회 메뉴");

		System.out.print("이름 : ");
		String name = inputString();

		System.out.print("주민번호 : ");
		String idNumber = inputString();

		try {
			System.out.println(notifyService.getListMember(name, idNumber));
			pause();
		} catch (RecordNotFoundException e) {
			print("잘못된 형식을 입력하셨습니다.");
			pause();

		}
		secondVaccineImfoMenu();
	}

	/**
	 * 2차 접종 알림 등록자 정보 변경 메뉴 
	 * @throws IOException
	 */
	private void setInfo() throws IOException {
		printTitle("내 정보 변경 메뉴");

		System.out.print("이름 : ");
		String name = inputString();

		System.out.print("전화번호 : ");
		String phoneNumber = inputString();

		System.out.print("주소 : ");
		String address = inputString();

		System.out.print("주민번호 : ");
		String idNumber = inputString();

		System.out.print("백신종류 : ");
		String vaccineName = inputString();

		System.out.print("1차 접종일 : ");
		String firstVaccine = inputString();

		ReserveMember dto = new ReserveMember(name, phoneNumber, address, idNumber,vaccineName, firstVaccine);

		try {
			notifyService.setMemberImfo(dto);
			pause();
		} catch (RecordNotFoundException e) {
			System.out.println("[오류] 맞지 않는 형식입니다.");
			pause();
		}
		secondVaccineImfoMenu();
	}

	/**
	 * 관리자 로그인 메뉴
	 * @throws RecordNotFoundException
	 * @throws CommonException
	 * @throws IOException 
	 */
	private void adminLogin() throws RecordNotFoundException, CommonException, IOException {
		printTitle("관리자 로그인 메뉴");
		
		System.out.print("아이디 : ");
		String id = inputString();

		System.out.print("비밀번호 : ");
		String pw = inputString();

		if(notifyService.adminLogin(id, pw)) {
			adminMainMenu();
		} else {
			mainMenu();
		}

	}


	/**
	 * 관리자 서비스 메뉴
	 * @throws IOException
	 */
	private void adminMainMenu() throws IOException {
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
			removeInfo();
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

	/**
	 * 관리자
	 * 접종자 수 등록 메뉴
	 * @throws IOException
	 */
	private void addVacciCountMenu() throws IOException {
		printTitle("접종자 수 등록");

		System.out.println("다음 고유번호 : " + (vacciService.getCount() + 1));
		
		System.out.print("고유번호 : ");
		int idKey = inputNumber();

		System.out.print("날짜 : ");
		String day = inputString();
		
		System.out.print("지역 : ");
		String region = inputString();
		
		System.out.print("1차 접종자 수 : ");
		int yesterdayFirst = inputNumber();
		
		System.out.print("2차 접종자 수 : ");
		int yesterdaySecond = inputNumber();
		
		System.out.print("누적 1차 접종자 수 : ");
		int totalFirst = inputNumber();
		
		System.out.print("누적 2차 접종자 수 : ");
		int totalSecond = inputNumber();

		VaccineCount dto = new VaccineCount(idKey, day, region, yesterdayFirst, yesterdaySecond, totalFirst, totalSecond );
		try {
			vacciService.addVacciCount(idKey, day, region, yesterdayFirst, yesterdaySecond, totalFirst, totalSecond);
			print("[등록 성공] " + "일자 : " + day + ", 지역 : " + region);
			pause();
			adminMainMenu();
		} catch (DuplicateException e) {
			print("[등록 실패]" + e.getMessage());
			pause();
			adminMainMenu();
		}

	}

	/**
	 * 관리자 
	 * 센터 정보 변경 메뉴
	 * @throws IOException
	 */
	private void setCenterInfoMenu() throws IOException {
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
			pause();
			adminMainMenu();
		} catch (RecordNotFoundException e) {
			print("[변경 실패]" + e.getMessage());
			pause();
			adminMainMenu();
		}
		
	}

	/**
	 * 관리자
	 * 백신 알림기능 신청자 정보 삭제 메뉴
	 */
	private void removeInfo() {
		printTitle("백신알림기능 신청자 정보 삭제");

		System.out.print("이름 : ");
		String name = inputString();

		try {
			notifyService.removeMemberList(name);
			System.out.println(notifyService.getList());
			pause();
			try {
				adminMainMenu();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (RecordNotFoundException e) {
			System.out.println("[오류] 없는 이름입니다.");
			pause();
			try {
				adminMainMenu();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	/**
	 * 바로 메인 메뉴로 넘어가지 않게 대기 시켜주는 메서드
	 */
	private void pause()  {
		System.out.println();
		System.out.println();
		System.out.println("계속하시려면 1번을, 종료하시려면 0번을 입력해주세요");
		int menuNo = inputNumber();
		switch(menuNo) {
		case 1:
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
	 * 구분선 출력
	 */
	public void printLine() {
		System.out.println("************************");
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
		try{
			return Integer.parseInt(data);
		} catch (NumberFormatException e) {
			
		}
		return -1;
	}
	
}
