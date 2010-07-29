package com.jimbarritt.spikes.stringtemplate.reflect;

import java.util.*;

import static java.lang.String.format;

public class StringTemplateArgument {
    private final String argumentName;
    private final String argumentValue;

    public StringTemplateArgument(String argumentName, String argumentValue) {
        this.argumentName = argumentName;
        this.argumentValue = argumentValue;
    }    

    public void addToMap(Map<String, Object> attributeMap) {
        attributeMap.put(argumentName, argumentValue);
    }

    public String toString() {
        return format("%s=%s", argumentName, argumentValue);
    }

    public static Map<String, Object> mapArgumentValues(List<StringTemplateArgument> arguments) {
        Map<String, Object> argumentMap = new HashMap<String, Object>();
        for (StringTemplateArgument argument : arguments) {
            argument.addToMap(argumentMap);
        }
        return argumentMap;
    }
}