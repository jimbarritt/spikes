package com.jimbarritt.spikes.stringtemplate.reflect;

import org.antlr.stringtemplate.*;

import java.util.*;

public class StringTemplateIntrospector {
    public static StringTemplateDefinition inspect(StringTemplate stringTemplate) {
        return new StringTemplateIntrospector().createStringTemplateDefinition(stringTemplate);
    }

    private StringTemplateDefinition createStringTemplateDefinition(StringTemplate stringTemplate) {
        return new StringTemplateDefinition(stringTemplate.getName());
    }
}