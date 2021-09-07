package com.ezypay.interview.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ezypay.interview.object.Invoice;
import com.ezypay.interview.object.Subscribe;

@Service
public class SubscriptionService {
	public Invoice getInvoice(Subscribe sub) {
		List<String> invoiceDates = new ArrayList<String>();
		DateTimeFormatter dft = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		LocalDate startDate = LocalDate.parse(sub.getStartDate(), dft);
		LocalDate endDate = LocalDate.parse(sub.getEndDate(), dft);
				
		switch (sub.getType()) {
		case DAILY:
			while(startDate.isBefore(endDate)) {				
				invoiceDates.add(dft.format(startDate));
				startDate = startDate.plusDays(1);
				
			}
			if(startDate.isEqual(endDate)) {
				invoiceDates.add(dft.format(startDate));
			}
			break;

		case WEEKLY:			
			while(startDate.isBefore(endDate) || startDate.isEqual(endDate)) {
				if(startDate.with(TemporalAdjusters.nextOrSame(this.dayMapper(sub.getBillable()))).isBefore(endDate) 
						|| startDate.with(TemporalAdjusters.nextOrSame(this.dayMapper(sub.getBillable()))).isEqual(endDate) ) {
					invoiceDates.add(dft.format(startDate.with(TemporalAdjusters.nextOrSame(this.dayMapper(sub.getBillable())))));
					//System.out.println(dft.format(startDate.with(TemporalAdjusters.nextOrSame(this.dayMapper(sub.getBillable())))));
					
				}
				startDate = startDate.plusWeeks(1);
			}
			break;			

		case MONTHLY:
			String[] dateSplit = sub.getStartDate().split("/");
			String date = sub.getBillable() +"/"+dateSplit[1]+"/"+dateSplit[2];
			LocalDate newDate = LocalDate.parse(date, dft);
			invoiceDates.add(dft.format(newDate));
			startDate = newDate.plusMonths(1);
			while(startDate.isBefore(endDate) ) {
				invoiceDates.add(dft.format(startDate));
				//System.out.println(dft.format(startDate));
				startDate = startDate.plusMonths(1);
			}
			break;
		}
		
		Invoice invoice = new Invoice(sub.getAmount(), sub.getType(), invoiceDates);
		return invoice;
	}
	
	public DayOfWeek dayMapper(String day) {
		if(day.equals("SUNDAY")) {
			return DayOfWeek.SUNDAY;
		}
		if(day.equals("MONDAY")) {
			return DayOfWeek.MONDAY;
		}
		if(day.equals("TUESDAY")) {
			return DayOfWeek.TUESDAY;
		}
		if(day.equals("WEDNESDAY")) {
			return DayOfWeek.WEDNESDAY;
		}
		if(day.equals("THURSDAY")) {
			return DayOfWeek.THURSDAY;
		}
		if(day.equals("FRIDAY")) {
			return DayOfWeek.FRIDAY;
		}
		if(day.equals("SUNDAY")) {
			return DayOfWeek.SUNDAY;
		}
		
		return null;		
	}
}
