package com.jimbarritt.spikes.restfulie.logging;

import org.apache.log4j.*;

import static java.lang.String.format;

public class StringFormatLogger {

    private final Logger delegateLogger;

    public static StringFormatLogger getStringFormatLogger(Class categoryClass) {
        return new StringFormatLogger(Logger.getLogger(categoryClass));
    }

    protected StringFormatLogger(Logger delegateLogger) {
        this.delegateLogger = delegateLogger;
    }

    public void info(String pattern, Object... parameters) {
        if (delegateLogger.isInfoEnabled()) {
            delegateLogger.info(format(pattern, parameters));
        }
    }
}