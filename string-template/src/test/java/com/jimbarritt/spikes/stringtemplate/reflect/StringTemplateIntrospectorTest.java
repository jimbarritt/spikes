package com.jimbarritt.spikes.stringtemplate.reflect;

import com.jimbarritt.spikes.stringtemplate.io.*;
import org.antlr.stringtemplate.*;
import org.apache.log4j.*;
import org.junit.*;

import java.util.*;

import static com.jimbarritt.spikes.stringtemplate.io.StringTemplateRootPath.getPathFor;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class StringTemplateIntrospectorTest {

    private static final Logger log = Logger.getLogger(StringTemplateIntrospectorTest.class);

    private Log4jStringTemplateErrorListener errorListener;
    private StandardStringTemplateGroupFactory factory;

    @Before
    public void onceBeforeEachTest() {
        errorListener = new Log4jStringTemplateErrorListener();
        factory = new StandardStringTemplateGroupFactory();
    }


    @Test
    public void canIntrospectSubTemplatesWithParameters() {
        StringTemplateGroup group = factory.createGroupFromRootPath(getPathFor("/st/htmlcomponent"), errorListener);
        StringTemplate manyComponentsTemplate = group.getInstanceOf("manyComponents");

        StringTemplateDefinition stringTemplateDefinition = StringTemplateIntrospector.inspect(manyComponentsTemplate);

        assertThat(stringTemplateDefinition.getName(), is("manyComponents"));
    }
}