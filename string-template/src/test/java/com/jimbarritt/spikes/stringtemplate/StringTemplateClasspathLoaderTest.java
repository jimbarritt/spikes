package com.jimbarritt.spikes.stringtemplate;

import org.junit.*;

public class StringTemplateClasspathLoaderTest {

    @Test(expected = StringTemplateException.class)
    public void failsIfResourceDoesNotExist() {
        StringTemplateClasspathLoader loader = new StringTemplateClasspathLoader();

        loader.loadGroup("foobar");
    }
}