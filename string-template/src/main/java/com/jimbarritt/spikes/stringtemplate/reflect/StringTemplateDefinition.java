package com.jimbarritt.spikes.stringtemplate.reflect;

import java.util.*;

import static java.lang.String.*;

public class StringTemplateDefinition {

    private String name;
    private List<StringTemplateInclude> includedTemplates;

    public StringTemplateDefinition(String name) {
        this(name, new ArrayList<StringTemplateInclude>());
    }

    public StringTemplateDefinition(String name, List<StringTemplateInclude> includedTemplates) {
        this.name = name;
        this.includedTemplates = includedTemplates;
    }    

    public String name() {
        return name;
    }

    public List<StringTemplateInclude> templateIncludes() {
        return includedTemplates;
    }   

    
}