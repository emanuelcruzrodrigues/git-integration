package com.git.integration.dao;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.git.integration.dao.HQLUtil;

public class HQLUtilTest {

	@Test
	public void test_addParameterLike() {
		Map<String, Object> parameters = new HashMap<>();
		
		HQLUtil.addParameterLike(parameters , "a", "test");
		assertEquals("%test%", parameters.get("a"));
		
		HQLUtil.addParameterLike(parameters , "b", "%test");
		assertEquals("%test", parameters.get("b"));
		
		HQLUtil.addParameterLike(parameters , "c", "test%");
		assertEquals("test%", parameters.get("c"));
	}

}
