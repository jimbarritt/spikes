package com.jimbarritt.spikes.stringtemplate.io;

import com.jimbarritt.spikes.stringtemplate.volume.*;
import org.antlr.stringtemplate.*;

import java.util.*;

public class InlineStringTemplateRenderer {
    private String template;
    private AttributeList attributes;

    public InlineStringTemplateRenderer template(String template) {
        this.template = template;
        return this;
    }

    String renderTemplate() {
        StringTemplate stringTemplate = new StringTemplate(template);
        stringTemplate.registerRenderer(Volume.class, new VolumeAttributeRenderer());
        for (Attribute attribute : attributes) {
            attribute.registerIn(stringTemplate);
        }
        return stringTemplate.toString();
    }

    public AttributeList with() {
        attributes = new AttributeList(this);
        return attributes;
    }

    public static class AttributeList implements Iterable<Attribute> {
        private List<Attribute> attributes = new ArrayList<Attribute>();
        private final InlineStringTemplateRenderer parent;

        public AttributeList(InlineStringTemplateRenderer parent) {
            this.parent = parent;
        }

        public AttributeList attribute(String key, Object value) {
            attributes.add(new Attribute(key, value));
            return this;
        }

        public String toString() {
            return parent.renderTemplate();
        }

        @Override public Iterator<Attribute> iterator() {
            return attributes.iterator();
        }
    }

    public static class Attribute {
        private String key;
        private Object value;

        public Attribute(String key, Object value) {
            this.key = key;
            this.value = value;
        }

        public void registerIn(StringTemplate stringTemplate) {
            stringTemplate.setAttribute(key, value);
        }
    }
}