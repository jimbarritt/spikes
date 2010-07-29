package com.jimbarritt.spikes.stringtemplate;

import com.jimbarritt.spikes.stringtemplate.reflect.*;

import java.util.*;
import java.util.regex.*;

import static java.lang.String.format;
import static java.util.regex.Pattern.compile;

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
                    ? componentKey(componentType, include.getArgumentValueAsString("id"))
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
            argument.addToMap(attributeMap);
        }
    }

    public void setAttributeForComponent(String componentType, String componentIdentifier, String attributeName, Object value) {
        String componentKey = componentKey(componentType, componentIdentifier);
        if (!componentAttributes.containsKey(componentKey)) {
            throw new StringTemplateIntrospectionException(format("Could not find component with identifier [%s]", componentIdentifier));
        }
        componentAttributes.get(componentKey).put(attributeName, value);
    }

    private static String componentKey(String componentType, String componentIdentifier) {
        return format("%s.%s", componentType, componentIdentifier);
    }
}