package com.restdemo.domain.usecase;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import com.restdemo.domain.model.Prices;
import com.restdemo.ports.out.FindPricesPersistence;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.slf4j.LoggerFactory;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
import uk.co.jemos.podam.api.RandomDataProviderStrategyImpl;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@RunWith(JUnit4.class)
public class FindPricesTest {

    private final FindPricesPersistence findPricesPersistence = mock(FindPricesPersistence.class);

    private final FindPrices findPrices = new FindPrices(findPricesPersistence);


    @Test
    public void findPrices() {
        // mock result object
        PodamFactory factory;
        Logger root = (Logger) LoggerFactory.getLogger("uk.co.jemos.podam.api");
        root.setLevel(Level.ERROR);
        RandomDataProviderStrategyImpl randomDataProviderStrategy = new RandomDataProviderStrategyImpl();
        randomDataProviderStrategy.setDefaultNumberOfCollectionElements(1);
        randomDataProviderStrategy.setMaxDepth(1);
        factory = new PodamFactoryImpl();
        factory.setStrategy(randomDataProviderStrategy);
        List lstReturn = List.of(factory.manufacturePojo(Prices.class), factory.manufacturePojo(Prices.class));

        // mocks
        when(findPricesPersistence.findByDateAndProductIdAndBrandId(any(), any(), any())).thenReturn(lstReturn);
        //call srv
        var result = findPrices.findPrices(new Date(), 1L, 1L);
        // validate result
        verify(findPricesPersistence, times(1)).findByDateAndProductIdAndBrandId(any(), any(), any());
        assertNotNull(result);
    }

    @Test
    public void findPrices_whenNullResult() {
        // mocks
        when(findPricesPersistence.findByDateAndProductIdAndBrandId(any(), any(), any())).thenReturn(null);
        //call srv
        var result = findPrices.findPrices(new Date(), 1L, 1L);
        // validate result
        verify(findPricesPersistence, times(1)).findByDateAndProductIdAndBrandId(any(), any(), any());
        assertNull(result);
    }
}
