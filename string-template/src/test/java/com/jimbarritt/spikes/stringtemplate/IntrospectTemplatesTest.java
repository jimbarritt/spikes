package com.jimbarritt.spikes.stringtemplate;

import com.jimbarritt.spikes.stringtemplate.io.*;
import org.antlr.stringtemplate.*;
import org.apache.log4j.*;
import org.junit.*;

import java.io.*;
import java.util.*;

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
    public void canFindDebugParametersAndTemplateNames() throws IOException {
        StringTemplateGroup group = factory.createGroupFromRootPath(getPathFor("/st/htmlcomponent"), errorListener);
        StringTemplate manyComponentsTemplate = group.getInstanceOf("manyComponents");

        log.info("Dependencies And Parameters of Template:\n" + printDependenciesAndParametersOf(manyComponentsTemplate));
    }


    private String getPathFor(String templatePath) {
        return getStringTemplateRootDir() + templatePath;        
    }

    @SuppressWarnings("unchecked")
    private static String printDependenciesAndParametersOf(StringTemplate template) {
        Map<String, Set> edges = new HashMap<String, Set>();
        template.getDependencyGraph(edges, true);
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Set> edge : edges.entrySet()) {
            String edgeName = edge.getKey();
            Set<String> elements = edge.getValue();
            sb.append("[").append(edgeName).append("]:\n");
            for (String includedTemplateName : elements) {
                sb.append("    ").append(includedTemplateName).append("\n");
                sb.append(printTemplateParameters(template.getGroup(), includedTemplateName));
            }            
        }
        return sb.toString();
    }

    private static String printTemplateParameters(StringTemplateGroup group, String includedTemplateName) {
        return "no parameters";
    }
}