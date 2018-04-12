package com.git.integration.converters;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToLocalDateConverter implements Converter<String, LocalDate>{

	@Override
	public LocalDate convert(String source) {
		try{
			return LocalDate.parse(source, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		}catch (Throwable e) {
			return null;
		}
	}

}