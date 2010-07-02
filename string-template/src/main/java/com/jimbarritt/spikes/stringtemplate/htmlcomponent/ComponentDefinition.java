package com.jimbarritt.spikes.stringtemplate.htmlcomponent;

import org.antlr.stringtemplate.*;

public class ComponentDefinition {
    public void configureTemplate(StringTemplate stringTemplate) {
        stringTemplate.setAttribute("define", this);
    }

    public String getComponent() {
        return "Component";
    }
}