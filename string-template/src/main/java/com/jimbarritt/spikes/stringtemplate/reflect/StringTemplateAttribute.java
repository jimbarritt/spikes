package com.jimbarritt.spikes.stringtemplate.reflect;

import org.antlr.stringtemplate.*;

public class StringTemplateAttribute {
    private final String attributeName;

    public StringTemplateAttribute(String attributeName) {
        this.attributeName = attributeName;
    }

    public String toString() {
        return attributeName;
    }

    public boolean isMissingIn(StringTemplate stringTemplate) {
        return (stringTemplate.getAttribute(attributeName) == null);
    }
}