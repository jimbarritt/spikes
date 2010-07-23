package com.jimbarritt.spikes.stringtemplate.io;

import com.jimbarritt.spikes.stringtemplate.volume.*;
import org.antlr.stringtemplate.*;

import java.util.*;

public class InlineStringTemplateRenderer {
    public static String renderStringTemplate(String template, StringTemplateAttribute... attributes) {
        StringTemplate stringTemplate = new StringTemplate(template);
        stringTemplate.registerRenderer(Volume.class, new VolumeAttributeRenderer());
        for (StringTemplateAttribute attribute : attributes) {
            attribute.registerIn(stringTemplate);
        }
        return stringTemplate.toString();
    }

    public static StringTemplateAttribute attribute(String key, Object value) {
        return new StringTemplateAttribute(key, value);
    }
    

    public static class StringTemplateAttribute {
        private String key;
        private Object value;

        public StringTemplateAttribute(String key, Object value) {
            this.key = key;
            this.value = value;
        }

        public void registerIn(StringTemplate stringTemplate) {
            stringTemplate.setAttribute(key, value);
        }
    }
}