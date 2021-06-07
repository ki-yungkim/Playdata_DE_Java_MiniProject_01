package com.work.util;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Utility {
	
	/**
	 * 백신 종류에 따라 2차 접종 예정일 계산
	 * @param vaccineName 백신 이름
	 * @param fisrtDate 1차 접종일
	 * @return 2차 접종일 
	 * @throws Exception
	 */
	public static String addDate(String vaccineName, String fisrtDate) throws Exception{
		SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd");
		
		Calendar cal = Calendar.getInstance();
		Date date = format.parse(fisrtDate);
		cal.setTime(date);
		
		switch(vaccineName) {
		case "화이자" :
			cal.add(Calendar.YEAR, 0);
			cal.add(Calendar.MONTH, 0);
			cal.add(Calendar.DATE, 21);
			break;
		case "모더나" :
			cal.add(Calendar.YEAR, 0);
			cal.add(Calendar.MONTH, 1);
			cal.add(Calendar.DATE, 0);
			break;
		case "아스트라제네카" :
			cal.add(Calendar.MONTH, 3);
			cal.add(Calendar.DATE, 0);
			break;
		
		}
		return format.format(cal.getTime()) ;
	}
	
	/**
	 * 천 단위 콤마 추가 메서드
	 * @param number 입력 숫자
	 * @return 천단위 콤마
	 */
	public static String commaThousand(int number) {
		return NumberFormat.getInstance().format(number);
	}
}
