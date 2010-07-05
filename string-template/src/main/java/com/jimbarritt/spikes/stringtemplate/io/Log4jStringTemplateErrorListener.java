package com.jimbarritt.spikes.stringtemplate.io;

import org.antlr.stringtemplate.*;
import org.apache.log4j.*;

import java.util.*;

public class Log4jStringTemplateErrorListener implements StringTemplateErrorListener {
    private static final Logger log = Logger.getLogger(Log4jStringTemplateErrorListener.class);
    private final List<String> errors = new ArrayList<String>();

    public Log4jStringTemplateErrorListener() {
    }

    @Override public void error(String msg, Throwable e) {
        log.error(msg, e);
        errors.add(msg);
    }

    @Override public void warning(String msg) {
        log.warn(msg);
    }

    public List<String> getErrors() {
        return errors;
    }
}