package com.restdemo.adapters.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.text.ParseException;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class DateUtilsTest {

    @Test
    public void parseDate() throws ParseException {
        var strDate = "25/01/2021 15:01:25";
        var date = DateUtils.parseDate(strDate);
        assertNotNull(date);
    }

    @Test(expected = ParseException.class)
    public void parseDate_whenWrongFormat() throws ParseException {
        var strDate = "25-01-2021 15:01:25";
        DateUtils.parseDate(strDate);
    }

    @Test
    public void parseDate_whenNullParam() throws ParseException {
        var date = DateUtils.parseDate(null);
        assertNull(date);
    }

    @Test
    public void isValidFormatDate() {
        var strDate = "25/01/2021 15:01:25";
        var date = DateUtils.isValidFormatDate(strDate);
        assertTrue(date);
    }

    @Test
    public void isValidFormatDate_whenFalse() {
        var strDate = "25-01-2021 15:01:25";
        var date = DateUtils.isValidFormatDate(strDate);
        assertFalse(date);
    }
}
