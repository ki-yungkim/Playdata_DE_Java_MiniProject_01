package com.work.exception;

/**
 * <pre>
 * CommonException 예외 처리 클래스 
 * </pre> 
 * @author 김기영
 * @version ver 1.0
 * @since jdk1.8
 */
public class CommonException extends Exception {
	public CommonException() {
		super("사용자 예외");
	}

	/**
	 * 사용자 예외 
	 * @param message 에러 메세지
	 */
	public CommonException(String message) {
		super("[오류] " + message);
	}	
}
