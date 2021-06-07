package com.work.exception;

/**
 * <pre>
 * RecordNotFoundException
 * 데이터 검색 예외 처리 클래스 
 * </pre> 
 * @author 김기영
 * @version ver 1.0
 * @since jdk1.8
 */
public class RecordNotFoundException extends Exception {
	public RecordNotFoundException() {
		super("데이터 검색 예외");
	}
	/**
	 * 데이터검색 에외
	 * @param message 오류메세지 
	 */
	public RecordNotFoundException(String message) {
		super("데이터 검색 예외 : " + message);
	}	
}
