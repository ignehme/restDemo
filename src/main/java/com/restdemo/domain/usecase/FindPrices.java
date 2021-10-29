package com.restdemo.domain.usecase;

import java.util.Date;

import com.restdemo.ports.in.FindPricesService;
import com.restdemo.domain.model.Prices;
import com.restdemo.ports.out.FindPricesPersistence;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
@AllArgsConstructor
public class FindPrices implements FindPricesService {

	private final FindPricesPersistence findPricesPersistence;

	@Override
	public Prices findPrices(Date date, Long brandId, Long productId) {
		Prices priceFound = null;
		var prices = findPricesPersistence.findByDateAndProductIdAndBrandId(date, productId, brandId);

		if (!CollectionUtils.isEmpty(prices)) {
			priceFound = prices.get(0);
		}
		return priceFound;
	}
}
