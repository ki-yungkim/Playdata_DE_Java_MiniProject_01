package com.work.util;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Utility {
	
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
	
	
	public static String commaThousand(int number) {
		return NumberFormat.getInstance().format(number);
	}
}
