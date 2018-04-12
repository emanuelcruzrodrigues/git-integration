package com.git.integration.dao;

import java.util.Map;

public class HQLUtil {
	
	public static void addParameterLike(Map<String, Object> parameters, String name, String value) {
		if (value.indexOf("%") >= 0) {
			parameters.put(name, value);
		}else {
			parameters.put(name, String.format("%%%s%%", value));
		}
	}

}
