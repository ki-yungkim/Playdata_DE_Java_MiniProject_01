package com.work.view;

import com.work.exception.DuplicateException;

/**
 * <pre>
 * 회원관리시스템 CUI 시작 클래스
 * </pre>
 *  
 * @author 김기영
 * @version ver.1.0
 * @since jdk1.8
 */
public class CUI {

	/**
	 * 회원관리시스템 CUI 시작 메서드
	 * @param args
	 * @throws DuplicateException 
	 */
	public static void main(String[] args) throws DuplicateException {
		/* 회원관리 메뉴 */
		Menu view = new Menu();
		
		/* 초기화 회원 등록 */
		//view.service.initMember();
		
		/* 초기화 메뉴 수행 */
		view.initMenu();
		
		while(true) {
			view.mainMenu();
		}
	}

}
