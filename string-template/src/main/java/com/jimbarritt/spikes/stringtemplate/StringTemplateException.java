package com.jimbarritt.spikes.stringtemplate;

public class StringTemplateException extends RuntimeException {

    public StringTemplateException(String message) {
        this(message, null);
    }

    public StringTemplateException(String message, Throwable cause) {
        super(message, cause);
    }

}