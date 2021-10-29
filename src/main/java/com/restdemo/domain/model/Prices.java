package com.restdemo.domain.model;

import lombok.*;

import java.util.Currency;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class Prices {

	private Integer priceList;

	private Long brandId;

	private Date startDate;
	
	private Date endDate;

	private Long productId;

	private Integer priority;

	private Double price;

	private Currency currency;
}
