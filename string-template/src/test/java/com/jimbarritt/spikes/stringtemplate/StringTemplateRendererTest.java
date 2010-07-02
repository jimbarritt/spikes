package com.jimbarritt.spikes.stringtemplate;

import org.antlr.stringtemplate.*;
import org.junit.*;

public class StringTemplateRendererTest {

    @Test(expected = StringTemplateRenderingException.class)
    public void failsIfThereIsAProblem() {
        StringTemplateGroup group = new StringTemplateGroup("testClasspathTemplateGroup");
        StringTemplate template = group.getInstanceOf("st/badTemplate");

        new StringTemplateRenderer().render(template);
    }
}
