package com.restdemo.adapters.rest.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Currency;
import java.util.Date;

import com.restdemo.adapters.rest.dto.PricesDto;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

class PricesMapperTest {

	@Test
	void shouldMapPricesToPricesDTO() {
		//GIVEN
		var prices = new PricesDto();
		prices.setPriceList(1);
		prices.setStartDate(new Date());
		prices.setEndDate(new Date());
		prices.setPrice(25.6);
		prices.setCurrency( Currency.getInstance("EUR"));
		var mapper = Mappers.getMapper(PricesMapper.class);
		var pricesDomain = mapper.toDomain(prices);
		//WHEN
		assertNotNull(pricesDomain);
		assertEquals(prices.getPrice(), pricesDomain.getPrice());
		assertEquals(prices.getPrice(), pricesDomain.getPrice());
	}
}
