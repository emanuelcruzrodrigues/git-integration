package com.git.integration.common;

import java.io.InputStream;

import org.apache.logging.log4j.LogManager;

public class CommandLineResultThread extends Thread{
	
	private final InputStream inputStream;
	private final StringBuilder result;
	
	public CommandLineResultThread(InputStream inputStream) {
		super();
		this.inputStream = inputStream;
		this.result = new StringBuilder();
	}

	@Override
	public void run() {
		
		StringBuilder line = new StringBuilder();
		
		try {
			int c;
			while((c = inputStream.read()) != -1) {
				char character = (char)c;
				result.append(character);
				
				if (character != '\n') {
					line.append(character);
				}else {
					LogManager.getLogger(getClass()).info(line.toString());
					line = new StringBuilder();
				}
				
			}
			
		} catch (Exception e) {
			DefaultExceptionHandler.handleException(e);
		}
		
	}
	
	public String getResult() {
		return result.toString();
	}

}
