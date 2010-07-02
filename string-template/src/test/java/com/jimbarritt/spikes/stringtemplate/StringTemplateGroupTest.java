package com.jimbarritt.spikes.stringtemplate;

import org.antlr.stringtemplate.*;
import org.apache.log4j.*;
import org.junit.*;

public class StringTemplateGroupTest {

    private static final Logger log = Logger.getLogger(StringTemplateGroupTest.class);

    @Test
    public void loadAStringTemplateGroup() throws Exception {
        StringTemplateGroup group = new StringTemplateClasspathLoader().loadGroup("st/solarsystem/simpleGroup.stg");
        StringTemplate template = group.getInstanceOf("outerTemplate");
        template.setAttribute("input", "Hello World");

        String result = new StringTemplateRenderer().render(template);

        log.info("Result\n" + result);
    }
}
