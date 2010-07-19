package com.jimbarritt.spikes.stringtemplate.reflect;

import com.jimbarritt.spikes.stringtemplate.io.*;
import org.antlr.stringtemplate.*;
import org.apache.log4j.*;
import org.junit.*;

import java.util.*;

import static com.jimbarritt.spikes.stringtemplate.io.StringTemplateRootPath.getPathFor;
import static com.jimbarritt.spikes.stringtemplate.reflect.StringTemplateIntrospector.inspect;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsNull.nullValue;
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

        StringTemplateDefinition stringTemplateDefinition = inspect(manyComponentsTemplate);

        assertThat(stringTemplateDefinition.name(), is("manyComponents"));

        List<StringTemplateInclude> invokedTemplates = stringTemplateDefinition.includedTemplates();

        assertThat(invokedTemplates.size(), is(4));

        StringTemplateInclude include_1 = invokedTemplates.get(0);
        assertThat(include_1.stringTemplateDefinition().name(), is("components/textComponent"));

        List<StringTemplateArgument> arguments = include_1.arguments();
        assertThat(arguments.size(), is(2));
        StringTemplateArgument argument_1 = arguments.get(0);
        assertThat(argument_1.name(), is("id"));
        assertThat(argument_1.value(), is("welcomeText"));

        StringTemplateArgument argument_2 = arguments.get(1);
        assertThat(argument_2.name(), is("text"));
        assertThat(argument_2.value(), is("<b>Some default text</b>"));
    }
}