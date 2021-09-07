package com.ezypay.interview.controller;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ezypay.interview.object.Invoice;
import com.ezypay.interview.object.Subscribe;
import com.ezypay.interview.service.SubscriptionService;

@RestController
@RequestMapping("subscribe")
public class SubscriptionController{
	@Autowired
	SubscriptionService subService;
	
	@PostMapping
	public ResponseEntity create(@RequestBody Subscribe sub) {
		DateTimeFormatter dft = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		LocalDate startDate = LocalDate.parse(sub.getStartDate(), dft);
		LocalDate endDate = LocalDate.parse(sub.getEndDate(), dft);
		
		System.out.println(Period.between(startDate, endDate).getMonths());
		if(Period.between(startDate, endDate).getMonths() > 3) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Subscription period more then 3 months not allowed!");
		}
		return ResponseEntity.ok(subService.getInvoice(sub));
	}
	

}
