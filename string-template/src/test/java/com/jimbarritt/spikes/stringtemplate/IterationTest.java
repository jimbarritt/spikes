package com.jimbarritt.spikes.stringtemplate;

import com.jimbarritt.spikes.stringtemplate.io.*;
import org.antlr.stringtemplate.*;
import org.apache.log4j.*;
import org.junit.*;

import java.util.*;

import static java.util.Arrays.asList;

public class IterationTest {
    private static final Logger log = Logger.getLogger(IterationTest.class);
    private StringTemplateGroup group;
    private List<String> listOfThings;

    @Before
    public void onceBeforeEachTest() {
        group = new StringTemplateLoader().loadGroupFromClasspath("st/formatting/list.stg");
        listOfThings = asList("foo", "bar", "choco", null, "monkey");
    }

    @Test
    public void rendersASimpleList() {
        StringTemplate template = group.getInstanceOf("simpleList");
        template.setAttribute("list", listOfThings);

        String representation = new StringTemplateRenderer().render(template);

        log.info("Representation\n" + representation);
    }

    @Test
    public void rendersAListWithASeparator() {
        StringTemplate template = group.getInstanceOf("listWithSeparator");
        template.setAttribute("list", listOfThings);

        String representation = new StringTemplateRenderer().render(template);

        log.info("Representation\n" + representation);
    }
}