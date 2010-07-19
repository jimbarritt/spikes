package com.jimbarritt.spikes.stringtemplate.reflect;

public class StringTemplateArgument {
    private final String argumentName;
    private final String argumentValue;

    public StringTemplateArgument(String argumentName, String argumentValue) {
        this.argumentName = argumentName;
        this.argumentValue = argumentValue;
    }

    public String name() {
        return argumentName;
    }

    public String value() {
        return argumentValue;
    }
}