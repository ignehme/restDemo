package com.restdemo.adapters.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

	private static final String DATE_FORMAT = "dd/MM/yyyy HH:mm:ss";

	private DateUtils(){}

	public static Date parseDate(String date) throws ParseException {

		if (date != null) {
			return new SimpleDateFormat(DATE_FORMAT).parse(date);
		}
		return null;
	}

	public static boolean isValidFormatDate(String date) {
		try {
			parseDate(date);
		} catch (ParseException e) {
			return false;
		}
		return true;
	}
}
