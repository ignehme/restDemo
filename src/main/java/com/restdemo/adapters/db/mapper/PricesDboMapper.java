package com.restdemo.adapters.db.mapper;

import com.restdemo.adapters.db.entities.PricesDbo;
import com.restdemo.domain.mapper.MapperBase;
import com.restdemo.domain.model.Prices;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PricesDboMapper extends MapperBase<Prices, PricesDbo> {
}
