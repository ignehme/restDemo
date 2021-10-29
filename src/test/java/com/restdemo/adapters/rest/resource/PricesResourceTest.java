package com.restdemo.adapters.rest.resource;

import com.restdemo.ports.in.FindPricesService;
import com.restdemo.domain.model.Prices;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Currency;
import java.util.Date;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@AutoConfigureJsonTesters
@WebMvcTest(PricesResource.class)
class PricesResourceTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
    FindPricesService pricesService;

	@Test
	void shoulBeStatusOk() throws Exception {

		var prices = new Prices();
		prices.setPriceList(1);
		prices.setStartDate(new Date());
		prices.setEndDate(new Date());
		prices.setPrice(25.6);
		prices.setCurrency(Currency.getInstance("EUR"));
		
		when(pricesService.findPrices(any(), any(), any())).thenReturn(prices);

		mockMvc.perform(get("/prices")).andExpect(status().isOk());

	}

	@Test
	void shoulBeStatusNotFound() throws Exception {

		when(pricesService.findPrices(any(), any(), any())).thenReturn(null);

		mockMvc.perform(get("/prices")).andExpect(status().isNotFound());

	}

}
