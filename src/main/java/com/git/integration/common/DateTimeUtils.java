package com.git.integration.common;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.TemporalAccessor;
import java.util.Locale;

public class DateTimeUtils {
	
	public static String format(TemporalAccessor time, Locale locale) {
		if (time == null) return null;
		return DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT, FormatStyle.SHORT).withLocale(locale).format(time);
	}

}
