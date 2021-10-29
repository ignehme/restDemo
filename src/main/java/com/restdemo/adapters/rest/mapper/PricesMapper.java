package com.restdemo.adapters.rest.mapper;

import com.restdemo.adapters.rest.dto.PricesDto;
import com.restdemo.domain.mapper.MapperBase;
import com.restdemo.domain.model.Prices;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PricesMapper extends MapperBase<Prices, PricesDto> {
}
