package com.jimbarritt.spikes.stringtemplate;

import com.jimbarritt.spikes.stringtemplate.io.*;
import org.antlr.stringtemplate.*;
import org.junit.*;

public class StringTemplateRendererTest {

    @Test(expected = StringTemplateException.class)
    public void failsIfThereIsAProblem() {
        StringTemplateGroup group = new StringTemplateGroup("testClasspathTemplateGroup");
        StringTemplate template = group.getInstanceOf("st/badTemplate");

        new StringTemplateRenderer().render(template);
    }
}
