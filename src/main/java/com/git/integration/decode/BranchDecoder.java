package com.git.integration.decode;

import java.util.ArrayList;
import java.util.List;

/**
 * @see BranchDecoderTest
 */
public class BranchDecoder {

	public List<String> decode(String string) {
		String[] strings = string.replace("\r", "").split("\n");
		
		List<String> result = new ArrayList<>();
		
		for (String branch : strings) {
			result.add(branch.replace("*", "").trim().replaceAll("\\s+", " "));
		}
		
		return result;
		
	}

}
