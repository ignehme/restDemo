package com.restdemo.adapters.db;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import com.restdemo.adapters.db.dao.PricesRepository;
import com.restdemo.adapters.db.entities.PricesDbo;
import com.restdemo.adapters.db.mapper.PricesDboMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.slf4j.LoggerFactory;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
import uk.co.jemos.podam.api.RandomDataProviderStrategyImpl;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(JUnit4.class)
public class FindPricesJpaTest {

    private final PricesRepository repository = mock(PricesRepository.class);

    private final PricesDboMapper mapper = mock(PricesDboMapper.class);

    private final FindPricesJpa findPrices = new FindPricesJpa(repository, mapper);

    @Test
    public void findByDateAndProductIdAndBrandId() {
        PodamFactory factory;
        Logger root = (Logger) LoggerFactory.getLogger("uk.co.jemos.podam.api");
        root.setLevel(Level.ERROR);
        RandomDataProviderStrategyImpl randomDataProviderStrategy = new RandomDataProviderStrategyImpl();
        randomDataProviderStrategy.setDefaultNumberOfCollectionElements(1);
        randomDataProviderStrategy.setMaxDepth(1);
        factory = new PodamFactoryImpl();
        factory.setStrategy(randomDataProviderStrategy);
        List lstReturn = List.of(factory.manufacturePojo(PricesDbo.class), factory.manufacturePojo(PricesDbo.class));

        // mocks
        when(repository.findByDateAndProductIdAndBrandId(any(), any(), any())).thenReturn(lstReturn);
        //call srv
        var result = findPrices.findByDateAndProductIdAndBrandId(new Date(), 1L, 1L);
        // validate result
        verify(repository, times(1)).findByDateAndProductIdAndBrandId(any(), any(), any());
        assertNotNull(result);
    }

    @Test
    public void findByDateAndProductIdAndBrandId_whenVoidResult() {
        // mocks
        when(repository.findByDateAndProductIdAndBrandId(any(), any(), any())).thenReturn(List.of());
        //call srv
        var result = findPrices.findByDateAndProductIdAndBrandId(new Date(), 1L, 1L);
        // validate result
        verify(repository, times(1)).findByDateAndProductIdAndBrandId(any(), any(), any());
        assertNotNull(result);
    }
}
