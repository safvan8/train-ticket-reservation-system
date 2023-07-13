package com.safvan.util;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExceptionLoggerUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionLoggerUtil.class);

	public static void logException(Throwable t, String requestURL) {

		LOGGER.error("Exception Occurred for the URL: {}", requestURL);
		LOGGER.error("Exception stackTrace: {}", Arrays.toString(t.getStackTrace()));
	}
}
