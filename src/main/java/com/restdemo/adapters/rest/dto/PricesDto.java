package com.restdemo.adapters.rest.dto;

import java.util.Currency;
import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PricesDto {
	
	
	private Integer priceList;

	private Date startDate;
	
	private Date endDate;

	private Double price;

	private Currency currency;


}
