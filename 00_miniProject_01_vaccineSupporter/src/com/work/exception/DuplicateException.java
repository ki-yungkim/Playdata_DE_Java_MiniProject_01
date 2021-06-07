package com.work.exception;

/**
 * <pre>
 * DuplicateException
 * 중복 예외 처리 클래스 
 * </pre> 
 * @author 김기영
 * @version ver 1.0
 * @since jdk1.8
 */
public class DuplicateException extends Exception {
	public DuplicateException() {
		super("중복 예외");
	}

	/**
	 * 중복 예외
	 * @param message 에러 메세지 
	 */
	public DuplicateException(String message) {
		super("중복 예외 : " + message);
	}	
}
