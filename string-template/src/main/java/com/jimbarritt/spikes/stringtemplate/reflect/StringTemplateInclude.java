package com.jimbarritt.spikes.stringtemplate.reflect;

import org.antlr.stringtemplate.*;
import sun.jvm.hotspot.interpreter.*;

import java.util.*;

public class StringTemplateInclude {
    private final StringTemplateDefinition stringTemplateDefinition;
    private final List<StringTemplateArgument> arguments;    

    public StringTemplateInclude(StringTemplateDefinition stringTemplateDefinition, List<StringTemplateArgument> arguments) {
        this.stringTemplateDefinition = stringTemplateDefinition;
        this.arguments = arguments;
    }


    public StringTemplateDefinition stringTemplateDefinition() {
        return stringTemplateDefinition;
    }
    
    public List<StringTemplateArgument> arguments() {
        return arguments;
    }


}