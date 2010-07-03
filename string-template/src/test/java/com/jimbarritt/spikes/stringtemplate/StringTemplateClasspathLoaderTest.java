package com.jimbarritt.spikes.stringtemplate;

import com.jimbarritt.spikes.stringtemplate.io.*;
import org.junit.*;

public class StringTemplateClasspathLoaderTest {

    @Test(expected = StringTemplateException.class)
    public void failsIfResourceDoesNotExist() {
        StringTemplateLoader loader = new StringTemplateLoader();
        loader.loadGroupFromClasspath("foobar");
    }
}