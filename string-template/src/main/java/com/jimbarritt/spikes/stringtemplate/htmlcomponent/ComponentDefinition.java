package com.jimbarritt.spikes.stringtemplate.htmlcomponent;

import org.antlr.stringtemplate.*;

import java.util.*;

public class ComponentDefinition extends HashMap<String, String> {

    public ComponentDefinition() {
        super.put("textComponent", "<div>This is my text component</div>");
    }

    public void configureTemplate(StringTemplate stringTemplate) {
        stringTemplate.setAttribute("define", this);
    }
    
}