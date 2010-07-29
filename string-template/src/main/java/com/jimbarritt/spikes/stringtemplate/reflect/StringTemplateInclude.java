package com.jimbarritt.spikes.stringtemplate.reflect;

import java.util.*;

import static com.jimbarritt.spikes.stringtemplate.reflect.StringTemplateArgument.mapArgumentValues;

public class StringTemplateInclude {
    private final StringTemplateDefinition stringTemplateDefinition;
    private final List<StringTemplateArgument> arguments;
    private final Map<String, Object> argumentValues;

    public StringTemplateInclude(StringTemplateDefinition stringTemplateDefinition, List<StringTemplateArgument> arguments) {
        this.stringTemplateDefinition = stringTemplateDefinition;
        this.arguments = arguments;
        this.argumentValues = mapArgumentValues(arguments);
    }


    public StringTemplateDefinition stringTemplateDefinition() {
        return stringTemplateDefinition;
    }
    
    public List<StringTemplateArgument> arguments() {
        return arguments;
    }

    public String name() {
        return stringTemplateDefinition.name();
    }

    @SuppressWarnings("unchecked")
    public <T> T getArgumentValue(String argumentName) {
        if (!argumentValues.containsKey(argumentName)) {
            throw new StringTemplateIntrospectionException(String.format("Could not find argument called [%s]", argumentName));
        }
        return (T)argumentValues.get(argumentName);
    }

    public boolean hasArgument(String argumentName) {
        return argumentValues.containsKey(argumentName);
    }

    public String toString() {
        return name();
    }

    public String getArgumentValueAsString(String argumentName) {
        Object value = getArgumentValue(argumentName);
        return (value == null) ? "" : value.toString();
    }
}