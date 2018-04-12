package com.git.integration.decode;

import java.util.ArrayList;
import java.util.List;

public class HistoryDecoder {
	
	public List<String> decode(String history) {
		history = "\n\n" + history;
		int start = 0;
		int end = 0;
		List<String> result = new ArrayList<>();
		
		do {
			start = history.indexOf("\ncommit", start+1);
			end = history.indexOf("\ncommit", start+1);
			if (end > 0) {
				result.add(history.substring(start+1, end));
			}else {
				result.add(history.substring(start+1));
			}
		} while (end >= 0);
		
		return result;
	}

}
