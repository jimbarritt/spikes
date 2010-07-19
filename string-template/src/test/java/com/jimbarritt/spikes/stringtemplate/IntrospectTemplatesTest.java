package com.jimbarritt.spikes.stringtemplate;

import com.jimbarritt.spikes.stringtemplate.io.*;
import com.jimbarritt.spikes.stringtemplate.reflect.*;
import org.antlr.stringtemplate.*;
import org.apache.log4j.*;
import org.junit.*;

import java.io.*;

import static com.jimbarritt.spikes.stringtemplate.io.StringTemplateRootPath.*;

public class IntrospectTemplatesTest {

    private static final Logger log = Logger.getLogger(IntrospectTemplatesTest.class);
    private Log4jStringTemplateErrorListener errorListener;
    private StringTemplateRenderer templateRenderer;
    private StandardStringTemplateGroupFactory factory;

    @Before
    public void onceBeforeEachTest() {
        errorListener = new Log4jStringTemplateErrorListener();
        templateRenderer = new StringTemplateRenderer();
        factory = new StandardStringTemplateGroupFactory();
    }

    @Test
    public void canFindOutAboutTemplates() throws IOException {
        StringTemplateGroup group = factory.createGroupFromRootPath(getPathFor("/st/htmlcomponent"), errorListener);

        StringTemplate manyComponentsTemplate = group.getInstanceOf("manyComponents");

        log.info("Found template [" + manyComponentsTemplate.getName() + "]");

        String representation = templateRenderer.render(manyComponentsTemplate);
        log.info("Representation:\n[[<<" + representation + ">>]]");

        StringTemplate template = manyComponentsTemplate.getDOTForDependencyGraph(true);
        String dotDependencyGraph = templateRenderer.render(template);
        log.info("DOT dependencyGraph: \n" + dotDependencyGraph);
    }

    @Test
    public void canDebugParametersAndTemplateNames() throws IOException {
        StringTemplateGroup group = factory.createGroupFromRootPath(getPathFor("/st/htmlcomponent"), errorListener);
        StringTemplate manyComponentsTemplate = group.getInstanceOf("manyComponents");

        StringTemplateTreePrinter printer = new StringTemplateTreePrinter();
        log.info("Dependencies And Parameters of Template:\n" + printer.printTreeOf(manyComponentsTemplate));
    }


}