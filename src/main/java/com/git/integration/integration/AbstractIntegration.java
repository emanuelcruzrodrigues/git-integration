package com.git.integration.integration;

import org.apache.logging.log4j.LogManager;

import com.git.integration.common.DefaultExceptionHandler;

public abstract class AbstractIntegration {
	
	private boolean running;
	
	public void run() {
		
		if (running) {
			LogManager.getLogger(getClass()).info(String.format("%s is already running", getClass().getSimpleName()));
			return;
		}
		
		try {
			running = true;
			
			runIntegration();
			
		}catch (Throwable e) {
			DefaultExceptionHandler.handleException(e);
			
		} finally {
			running = false;
		}
		
	}

	abstract void runIntegration();
	

}
