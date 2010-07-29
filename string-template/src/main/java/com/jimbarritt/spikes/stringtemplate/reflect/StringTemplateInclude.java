package com.jimbarritt.spikes.stringtemplate.reflect;

import org.antlr.stringtemplate.*;
import sun.jvm.hotspot.interpreter.*;

import java.util.*;

public class StringTemplateInclude {
    private final StringTemplateDefinition stringTemplateDefinition;
    private final List<StringTemplateArgument> arguments;
    private final Map<String,  StringTemplateArgument> argumentMap;

    public StringTemplateInclude(StringTemplateDefinition stringTemplateDefinition, List<StringTemplateArgument> arguments) {
        this.stringTemplateDefinition = stringTemplateDefinition;
        this.arguments = arguments;
        this.argumentMap = initialiseArgumentMap(this.arguments);
    }

    private static Map<String, StringTemplateArgument> initialiseArgumentMap(List<StringTemplateArgument> arguments) {
        Map<String, StringTemplateArgument> argumentMap = new HashMap<String, StringTemplateArgument>();
        for (StringTemplateArgument argument : arguments) {
            argumentMap.put(argument.name(), argument);
        }
        return argumentMap;
    }

    public StringTemplateDefinition stringTemplateDefinition() {
        return stringTemplateDefinition;
    }
    
    public List<StringTemplateArgument> arguments() {
        return arguments;
    }

    public String getArgument(String identifyingArgumentName) {
        if (!argumentMap.containsKey(identifyingArgumentName)) {
            return "";
        }
        return argumentMap.get(identifyingArgumentName).value();
    }

    public void setAttributes(Map<String, Object> templateAttributes) {

    }
}