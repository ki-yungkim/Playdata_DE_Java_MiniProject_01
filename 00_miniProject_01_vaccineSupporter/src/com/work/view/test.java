package com.work.view;

import com.work.exception.DuplicateException;
import com.work.exception.RecordNotFoundException;
import com.work.service.CenterService;
import com.work.service.ReserveNotifyService;
import com.work.service.VaccineCountService;
import com.work.util.Utility;

import sun.print.resources.serviceui;

public class test {
	public static CenterService service = new CenterService();
	public ReserveNotifyService notifyService = new ReserveNotifyService();
	public static VaccineCountService vacciService = new VaccineCountService();
	public Utility utility = new Utility();

	public static void main(String[] args) {
		System.out.println();
			try {
				service.initCenter();
				
				
			} catch (DuplicateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
	}

	

		
	
}
