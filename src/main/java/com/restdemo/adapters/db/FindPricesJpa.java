package com.restdemo.adapters.db;

import com.restdemo.adapters.db.dao.PricesRepository;
import com.restdemo.adapters.db.dao.SpringDataGenericRepository;
import com.restdemo.adapters.db.entities.PricesDbo;
import com.restdemo.adapters.db.mapper.PricesDboMapper;
import com.restdemo.domain.mapper.MapperBase;
import com.restdemo.domain.model.Prices;
import com.restdemo.ports.out.FindPricesPersistence;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class FindPricesJpa extends JpaBase<Prices, PricesDbo, Long> implements FindPricesPersistence {

    private final PricesRepository repository;

    private final PricesDboMapper mapper;

    @Override
    protected SpringDataGenericRepository<PricesDbo, Long> getRepository() {
        return repository;
    }

    @Override
    protected MapperBase<Prices, PricesDbo> getMapper() {
        return mapper;
    }

    @Override
    public List<Prices> findByDateAndProductIdAndBrandId(Date date, Long productId, Long brandId) {
        return repository.findByDateAndProductIdAndBrandId(date, productId, brandId)
                .stream().map(mapper::toDomain).collect(Collectors.toList());
    }
}
