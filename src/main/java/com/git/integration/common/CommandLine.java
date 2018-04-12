package com.git.integration.common;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import org.apache.commons.lang3.SystemUtils;
import org.apache.logging.log4j.LogManager;

public class CommandLine {
	
	public String run(String ... commands) {
		try {
			
			String start = getStartCommand();
			
			ProcessBuilder processBuilder = new ProcessBuilder(start);
			processBuilder.redirectErrorStream(true);
			
			Process process = processBuilder.start();
			
			CommandLineResultThread resultThread = new CommandLineResultThread(process.getInputStream());
			resultThread.start();
			
			try(OutputStream outputStream = process.getOutputStream()){
				BufferedWriter prompt = new BufferedWriter(new OutputStreamWriter(outputStream));

				for (String command : commands) {
					LogManager.getLogger(getClass()).info(command);
					run(prompt, command);
				}

				run(prompt, "exit");
				process.waitFor();
			}
			
			Thread.sleep(1000);
			
			return resultThread.getResult();
			
		} catch (Exception e) {
			DefaultExceptionHandler.handleException(e);
			return e.getMessage();
		}
	}

	private String getStartCommand() {
		if (SystemUtils.IS_OS_WINDOWS) return "cmd";
		if (SystemUtils.IS_OS_LINUX) return "/bin/bash";
		throw new RuntimeException("Your OS is not supported");
	}

	private void run(BufferedWriter prompt, String command) throws IOException {
		prompt.write(command);
		prompt.newLine();
		prompt.flush();
	}

}
