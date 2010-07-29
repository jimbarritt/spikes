package com.jimbarritt.spikes.stringtemplate;

import com.jimbarritt.spikes.stringtemplate.reflect.*;
import org.antlr.stringtemplate.*;

import java.util.*;
import java.util.regex.*;

import static java.lang.String.*;
import static java.util.regex.Pattern.*;

public class ComponentAttributeMap {
    private final Map<String, Map<String, Object>> componentAttributes = new HashMap<String, Map<String, Object>>();

    private static final Pattern COMPONENT_NAME_PATTERN = compile("(.*/)*(.*)");

    public ComponentAttributeMap() {
    }

    public static ComponentAttributeMap loadDefaultsFrom(StringTemplateDefinition stringTemplateDefinition) {
        ComponentAttributeMap componentAttributeMap = new ComponentAttributeMap();
        for (StringTemplateInclude include : stringTemplateDefinition.templateIncludes()) {
            String componentType = extractComponentTypeFrom(include.name());

            String componentIdentifier = (include.hasArgument("id"))
                    ? include.getArgumentValueAsString("id")
                    : componentType;

            componentAttributeMap.addAttributes(componentIdentifier, include.arguments());
        }
        return componentAttributeMap;
    }

    private static String extractComponentTypeFrom(String templateName) {
        Matcher matcher = COMPONENT_NAME_PATTERN.matcher(templateName);
        if (!matcher.matches()) {
            throw new StringTemplateIntrospectionException(format("Could not parse templateName [%s] with regex [%s]", templateName, COMPONENT_NAME_PATTERN.pattern()));
        }
        return matcher.group(2);
    }

    private void addAttributes(String componentIdentifier, List<StringTemplateArgument> arguments) {
        if (!componentAttributes.containsKey(componentIdentifier)) {
            componentAttributes.put(componentIdentifier, new HashMap<String, Object>());
        }
        Map<String, Object> attributeMap = componentAttributes.get(componentIdentifier);
        for (StringTemplateArgument argument : arguments) {
            if (argument.nameIsNot("id")) {
                argument.addToMap(attributeMap);
            }
        }
    }

    public void setAttributeForComponent(String componentIdentifier, String attributeName, Object value) {
        if (!componentAttributes.containsKey(componentIdentifier)) {
            throw new StringTemplateIntrospectionException(format("Could not find component with identifier [%s]", componentIdentifier));
        }
        componentAttributes.get(componentIdentifier).put(attributeName, value);
    }
    
    public void populateTemplateAttributes(StringTemplate stringTemplate) {
        Map<String, Object> componentParameters = new HashMap<String, Object>();
        for (String key : componentAttributes.keySet()) {
            populateTemplateAttributes(componentParameters, key, componentAttributes.get(key));
        }
        stringTemplate.setAttribute("componentParameters", componentParameters);
    }

    private void populateTemplateAttributes(Map<String, Object> attributeMap, String componentIdentifier, Map<String, Object> attributes) {
        for (String key : attributes.keySet()) {
            attributeMap.put(format("%s_%s", componentIdentifier, key), attributes.get(key));
        }
    }
}