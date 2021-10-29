package com.restdemo.domain.model;

import org.junit.Test;

public class ModelTest extends BeanTest {

    @Test
    public void testModel() {
        checkAccessor(new Prices());
    }
}
