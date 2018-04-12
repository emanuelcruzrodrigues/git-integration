package com.git.integration.converters;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class LocalDateToStringConverter implements Converter<LocalDate, String>{

	@Override
	public String convert(LocalDate source) {
		if (source == null) return null;
		return DateTimeFormatter.ofPattern("yyyy-MM-dd").format(source);
	}

}
