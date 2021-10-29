package com.restdemo.adapters.rest.resource;

import com.restdemo.adapters.rest.dto.PricesDto;
import com.restdemo.adapters.utils.DateUtils;
import com.restdemo.ports.in.FindPricesService;
import com.restdemo.adapters.rest.mapper.PricesMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping(value = "/prices")
@RequiredArgsConstructor
@Slf4j
public class PricesResource {

	private final FindPricesService pricesService;


	//TODO pathVariable
	@GetMapping
	public ResponseEntity<PricesDto> get(@RequestParam(value = "date", required = false) String date,
                                         @RequestParam(value = "productId", required = false) Long productId,
                                         @RequestParam(value = "brandId", required = false) Long brandId) throws ParseException {

		//TODO validate request
		if (!DateUtils.isValidFormatDate(date)) {
			log.info("Date format is invalid. It should be dd/mm/yyyy HH:mm:ss");
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		var mapper = Mappers.getMapper(PricesMapper.class);
		var prices = mapper.toAdapter(pricesService.findPrices(DateUtils.parseDate(date), brandId, productId));

		if (prices == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(prices, HttpStatus.OK);
	}

}
