package com.jimbarritt.spikes.stringtemplate.reflect;

import com.jimbarritt.spikes.stringtemplate.io.*;
import org.antlr.stringtemplate.*;
import org.apache.log4j.*;
import org.junit.*;

import java.util.*;

import static com.jimbarritt.spikes.stringtemplate.io.StringTemplateRootPath.*;
import static com.jimbarritt.spikes.stringtemplate.reflect.StringTemplateIntrospector.*;
import static org.hamcrest.core.Is.*;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsSame.sameInstance;
import static org.junit.Assert.*;

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
    public void templateInstancesAreTheSame() {
        StringTemplateGroup group = factory.createGroupFromRootPath(getPathFor("/st/htmlcomponent"), errorListener);
        StringTemplate textComponent1 = group.getInstanceOf("components/textComponent");
        StringTemplate textComponent2 = group.getInstanceOf("components/textComponent");

        assertThat(textComponent1, is(not(sameInstance(textComponent2))));
    }
    
    @Test
    public void canIntrospectSubTemplatesWithParameters() {
        StringTemplateGroup group = factory.createGroupFromRootPath(getPathFor("/st/htmlcomponent"), errorListener);
        StringTemplate manyComponentsTemplate = group.getInstanceOf("manyComponents");

        StringTemplateDefinition stringTemplateDefinition = inspect(manyComponentsTemplate);

        assertThat(stringTemplateDefinition.name(), is("manyComponents"));

        List<StringTemplateInclude> includedTemplates = stringTemplateDefinition.templateIncludes();

        assertThat(includedTemplates.size(), is(4));

        StringTemplateInclude include_1 = includedTemplates.get(0);
        assertThat(include_1.stringTemplateDefinition().name(), is("components/textComponent"));

        List<StringTemplateArgument> arguments = include_1.arguments();
        assertThat(arguments.size(), is(3));
        StringTemplateArgument argument_1 = arguments.get(0);
        assertThat(argument_1.toString(), is("id=welcomeMessage"));

        StringTemplateArgument argument_2 = arguments.get(1);
        assertThat(argument_2.toString(), is("text=<strong>Some default text</strong>"));

        StringTemplateArgument argument_3 = arguments.get(2);
        assertThat(argument_3.toString(), is("editable=false"));        
    }

    @Test
    public void knowsIfAnAttributeWasRequiredButNotProvided() {
        StringTemplate stringTemplate = new StringTemplate("$attrA$, $attrB$");
        stringTemplate.setErrorListener(errorListener);
        stringTemplate.setAttribute("attrA", "foo");

        String representation = stringTemplate.toString();

        log.info(representation);

        log.info(new StringTemplateTreePrinter().printTreeOf(stringTemplate));


        StringTemplateDefinition definition = StringTemplateIntrospector.inspect(stringTemplate);

        List<StringTemplateAttribute> attributesNotInTemplate = definition.whichAttributesAreMissingIn(stringTemplate);
        assertThat(attributesNotInTemplate.size(), is(1));
        assertThat(attributesNotInTemplate.get(0).toString(), is("attrB"));
    }


}