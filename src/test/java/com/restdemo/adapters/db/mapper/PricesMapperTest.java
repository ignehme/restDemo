package com.restdemo.adapters.db.mapper;

import com.restdemo.adapters.db.entities.PricesDbo;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.Currency;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class PricesMapperTest {

    @Test
    void shouldMapPricesToPricesDbo() {
        //GIVEN
        var prices =
                PricesDbo.builder()
                        .priceList(1)
                        .startDate(new Date())
                        .endDate(new Date())
                        .price(25.6)
                        .currency( Currency.getInstance("EUR")).build();
        var mapper = Mappers.getMapper(PricesDboMapper.class);
        var pricesDomain = mapper.toDomain(prices);
        //WHEN
        assertNotNull(pricesDomain);
        assertEquals(prices.getPrice(), pricesDomain.getPrice());
        assertEquals(prices.getPrice(), pricesDomain.getPrice());
    }
}
