package com.git.integration.common;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;

public class DefaultExceptionHandler {
	
	public static void handleException(Throwable e) {
		LogManager.getLogger().error(ExceptionUtils.getStackTrace(e));
	}

}
