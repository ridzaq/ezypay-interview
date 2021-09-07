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
public class Invoice {
	private double amount;
	private Type type;
	private List<String> invoiceDates;
}
