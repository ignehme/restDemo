package com.restdemo.ports.out;

import com.restdemo.domain.model.Prices;

import java.util.Date;
import java.util.List;

public interface FindPricesPersistence extends PersistenceBase<Prices, Long> {

    List<Prices> findByDateAndProductIdAndBrandId(Date date, Long productId, Long brandId);
}
