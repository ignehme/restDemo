package com.restdemo.adapters.rest.dto;

import com.restdemo.domain.model.BeanTest;
import org.junit.Test;

public class DtoTest extends BeanTest {

    @Test
    public void testModel() {
        checkAccessor(new ErrorDto());
        checkAccessor(new PricesDto());
    }
}
