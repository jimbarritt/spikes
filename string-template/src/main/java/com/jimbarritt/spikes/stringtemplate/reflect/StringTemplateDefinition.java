package com.jimbarritt.spikes.stringtemplate.reflect;

import com.jimbarritt.spikes.stringtemplate.io.*;
import org.antlr.stringtemplate.*;

import java.util.*;

import static com.jimbarritt.spikes.stringtemplate.reflect.StringTemplateDefinition.IdentifierAttributePair.*;
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

    public List<StringTemplateInclude> includedTemplates() {
        return includedTemplates;
    }

    /**
     * Allows you to populate the properties of included templates based on an identifying property. For example if you
     * include a template like this:
     * <p/>
     * <pre>
     * $components/textComponent(id="aboutMe")$
     * </pre>
     * <p/>
     * This method will allow you to do something like this:
     * <p/>
     * <pre>
     *      Map<String, Object> attributesToSet = new HashMap<String, Object>();
     *      attributesToSet.put("aboutMe.text", "Hello, I'm your friend");
     *      definition.setIncludedTemplateAttributes("id", attributesToSet);
     * </pre>
     */
    public void populateIncludedTemplateAttributes(String identifyingAttributeName, Map<String, Object> attributes) {
        AttributesByTemplateMap attributesByIncludedTemplate = attributesByIncludedTemplate(attributes);
        for (StringTemplateInclude include : includedTemplates()) {
            String templateIdentifier = include.getArgument(identifyingAttributeName);
            Map<String, Object> templateAttributes = attributesByIncludedTemplate.get(templateIdentifier);
            include.setAttributes(templateAttributes);
        }
    }

    private static AttributesByTemplateMap attributesByIncludedTemplate(Map<String, Object> attributes) {
        AttributesByTemplateMap attributesByTemplateMap = new AttributesByTemplateMap();

        for (Map.Entry<String, Object> entry : attributes.entrySet()) {
            IdentifierAttributePair idNamePair = fromMapKey(entry.getKey());
            attributesByTemplateMap.addAttribute(idNamePair, entry.getValue());
        }

        return attributesByTemplateMap;
    }

    private static class AttributesByTemplateMap {
        private final Map<String, Map<String, Object>> attributeTemplateMap = new HashMap<String, Map<String, Object>>();

        public void addAttribute(IdentifierAttributePair idAttributePair, Object value) {
            if (!attributeTemplateMap.containsKey(idAttributePair.attributeName())) {
                attributeTemplateMap.put(idAttributePair.templateIdentifier(), new HashMap<String, Object>());
            }
            attributeTemplateMap.get(idAttributePair.templateIdentifier).put(idAttributePair.attributeName(), value);
        }

        public Map<String, Object> get(String templateIdentifier) {
            return attributeTemplateMap.get(templateIdentifier);
        }

    }

    static class IdentifierAttributePair {
        private final String templateIdentifier;
        private final String attributeName;

        public static IdentifierAttributePair fromMapKey(String entryKey) {
            String[] pair = entryKey.split("\\.");
            if (pair.length != 2) {
                throw new IllegalArgumentException(format("Could not parse entryKey [%s] should be in the format {identifier}.{attributeName}", entryKey));
            }
            return new IdentifierAttributePair(pair[0], pair[1]);
        }

        private IdentifierAttributePair(String templateIdentifier, String attributeName) {
            this.templateIdentifier = templateIdentifier;
            this.attributeName = attributeName;
        }

        public String templateIdentifier() {
            return this.templateIdentifier;
        }

        public String attributeName() {
            return this.attributeName;
        }
    }
}