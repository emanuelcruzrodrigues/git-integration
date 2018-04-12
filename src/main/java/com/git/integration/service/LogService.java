package com.git.integration.service;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

import org.apache.commons.io.input.ReversedLinesFileReader;
import org.springframework.stereotype.Service;

import com.git.integration.common.DefaultExceptionHandler;

@Service
public class LogService {
	
	public String getLog() {
		File file = new File("logs/git-integration.log");

		String log = new String();
		String line = null;
		int rows = 0;
		try (ReversedLinesFileReader reader = new ReversedLinesFileReader(file, Charset.defaultCharset())) {
			do {
				line = reader.readLine();
				if (line != null) {
					log = line + "\n" + log;
				}
				rows++;
			} while (rows < 1000 && line != null);
		} catch (IOException e) {
			DefaultExceptionHandler.handleException(e);
		}
		
		return log.toString();
	}

}
