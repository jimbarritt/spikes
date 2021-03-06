package com.jimbarritt.spikes.stringtemplate.reflect;

import com.jimbarritt.spikes.stringtemplate.*;
import com.jimbarritt.spikes.stringtemplate.io.*;
import org.antlr.stringtemplate.*;
import org.apache.log4j.*;
import org.junit.*;

import java.util.*;

import static com.jimbarritt.spikes.stringtemplate.ComponentAttributeMap.*;
import static com.jimbarritt.spikes.stringtemplate.io.StringTemplateRootPath.*;
import static com.jimbarritt.spikes.stringtemplate.reflect.StringTemplateIntrospector.*;
import static java.lang.String.format;
import static org.junit.Assert.*;
import static org.junit.internal.matchers.StringContains.*;

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
    public void printTemplateStructure() {
        StringTemplateGroup group = factory.createGroupFromRootPath(getPathFor("/st/htmlcomponent"), errorListener);
        StringTemplate imageComponent = group.getInstanceOf("components/imageComponent");

        String syntaxTree = new StringTemplateTreePrinter().printTreeOf(imageComponent);
        log.debug(format("Syntax Tree:\n%s", syntaxTree));
    }

    @Test
    public void canSetWelcomeMessageText() {
        StringTemplateGroup group = factory.createGroupFromRootPath(getPathFor("/st/htmlcomponent"), errorListener);
        StringTemplate manyComponentsTemplate = group.getInstanceOf("manyComponents");
        
        Map<String, Object> attributes = new HashMap<String, Object>();
        attributes.put("aboutMe_text", "Hello from Jim!");
        manyComponentsTemplate.setAttribute("componentParameters", attributes);

        String representation = new StringTemplateRenderer().render(manyComponentsTemplate);

        log.info("ManyComponents:\n" + representation);

        assertThat(representation, containsString("Hello from Jim!"));
    }

    @Test
    public void rendersDefaultProperty() {
        StringTemplateGroup group = factory.createGroupFromRootPath(getPathFor("/st/htmlcomponent"), errorListener);
        StringTemplate manyComponentsTemplate = group.getInstanceOf("manyComponents");

        StringTemplateDefinition templateDefinition = inspect(manyComponentsTemplate);
        ComponentAttributeMap componentAttributeMap = loadDefaultsFrom(templateDefinition);
        componentAttributeMap.setAttributeForComponent("aboutMe", "text", "A New About Message");

        componentAttributeMap.populateTemplateAttributes(manyComponentsTemplate);

        String representation = new StringTemplateRenderer().render(manyComponentsTemplate);

        log.info("ManyComponents:\n" + representation);

        assertThat(representation, containsString("<strong>Some default text $StringTemplate$ </strong>"));
        assertThat(representation, containsString("A New About Message"));
    }
}