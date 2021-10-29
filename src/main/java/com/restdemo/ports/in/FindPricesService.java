package com.restdemo.ports.in;

import com.restdemo.domain.model.Prices;

import java.util.Date;


public interface FindPricesService {

	/**
	 * 
	 * Find Prices By Params
	 * 
	 * @param date
	 * @param brandId
	 * @param productId
	 * @return
	 */
	public Prices findPrices(Date date, Long brandId, Long productId);

}
