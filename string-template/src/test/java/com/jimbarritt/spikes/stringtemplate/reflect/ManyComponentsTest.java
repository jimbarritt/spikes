package com.jimbarritt.spikes.stringtemplate.reflect;

import static com.jimbarritt.spikes.stringtemplate.io.StringTemplateRootPath.*;
import static com.jimbarritt.spikes.stringtemplate.reflect.StringTemplateIntrospector.inspect;
import static org.junit.Assert.assertThat;
import static org.junit.internal.matchers.StringContains.containsString;

import com.jimbarritt.spikes.stringtemplate.io.*;
import org.antlr.stringtemplate.*;
import org.apache.log4j.*;
import org.junit.*;

import java.util.*;

public class ManyComponentsTest {

    private static final Logger log = Logger.getLogger(ManyComponentsTest.class);

    private Log4jStringTemplateErrorListener errorListener;
    private StandardStringTemplateGroupFactory factory;

    @Before
    public void onceBeforeEachTest() {
        errorListener = new Log4jStringTemplateErrorListener();
        factory = new StandardStringTemplateGroupFactory();
    }

    @Test
    public void printoutManyComponentsResult() {
        StringTemplateGroup group = factory.createGroupFromRootPath(getPathFor("/st/htmlcomponent"), errorListener);
        StringTemplate manyComponentsTemplate = group.getInstanceOf("manyComponents");

        Map<String, String> attributes = new HashMap<String, String>();
        attributes.put("welcomeMessageText", "Hello from Jim!");
        manyComponentsTemplate.setAttribute("componentParameters", attributes);
        
        String representation = new StringTemplateRenderer().render(manyComponentsTemplate);

        log.info("ManyComponents:\n" + representation);

        assertThat(representation, containsString("Hello from Jim!"));
    }
}