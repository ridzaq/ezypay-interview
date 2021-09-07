package com.ezypay.interview.object;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Subscribe {
	private double amount;
	private Type type; //DAILY/WEEKLY/MONTHLY
	private String billable; // if WEEKLY day of week i.e TUESDAY, if MONTHLY  then date, i.e 24
	private String startDate; // dd/mm/yyyy format
	private String endDate; // dd/mm/yyyy format
}
