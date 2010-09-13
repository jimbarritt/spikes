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

    public void debug(String pattern, Object... parameters) {
        if (delegateLogger.isDebugEnabled()) {
            delegateLogger.debug(format(pattern, parameters));
        }
    }

    public void error(String message, Throwable t) {
        delegateLogger.error(format(message, t));
    }
    public void error(String pattern, Throwable t, Object... parameters) {
        delegateLogger.error(format(pattern, parameters), t);
    }


}