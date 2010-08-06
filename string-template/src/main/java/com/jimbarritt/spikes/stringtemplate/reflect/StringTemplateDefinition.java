package com.jimbarritt.spikes.stringtemplate.reflect;

import org.antlr.stringtemplate.*;

import java.util.*;

public class StringTemplateDefinition {

    private String name;
    private List<StringTemplateInclude> includedTemplates;
    private List<StringTemplateAttribute> referencedAttributes;

    public StringTemplateDefinition(String name) {
        this(name, new ArrayList<StringTemplateInclude>(), new ArrayList<StringTemplateAttribute>());
    }

    public StringTemplateDefinition(String name, List<StringTemplateInclude> includedTemplates, List<StringTemplateAttribute> referencedAttributes) {
        this.name = name;
        this.includedTemplates = includedTemplates;
        this.referencedAttributes = referencedAttributes;
    }

    public String name() {
        return name;
    }

    public List<StringTemplateInclude> templateIncludes() {
        return includedTemplates;
    }

    public List<StringTemplateAttribute> whichAttributesAreMissingIn(StringTemplate stringTemplate) {
        List<StringTemplateAttribute> missingAttributes = new ArrayList<StringTemplateAttribute>();
        for (StringTemplateAttribute attribute : referencedAttributes) {
            if (attribute.isMissingIn(stringTemplate)) {
                missingAttributes.add(attribute);
            }
        }
        return missingAttributes;
    }
}