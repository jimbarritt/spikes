package com.jimbarritt.spikes.stringtemplate.reflect;

import java.util.*;

public class StringTemplateDefinition {

    private String name;
    private List<StringTemplateInclude> invokedTemplates;

    public StringTemplateDefinition(String name) {
        this(name, new ArrayList<StringTemplateInclude>());
    }

    public StringTemplateDefinition(String name, List<StringTemplateInclude> invokedTemplates) {
        this.name = name;
        this.invokedTemplates = invokedTemplates;
    }

    public String name() {
        return name;
    }

    public List<StringTemplateInclude> includedTemplates() {
        return invokedTemplates;
    }
}