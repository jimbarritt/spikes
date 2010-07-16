package com.jimbarritt.spikes.stringtemplate;

import com.jimbarritt.spikes.stringtemplate.io.*;
import org.antlr.stringtemplate.*;
import org.apache.log4j.*;
import org.junit.*;

import java.io.*;
import java.util.*;

import static com.jimbarritt.spikes.stringtemplate.io.StringTemplateRootPath.getStringTemplateRootDir;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

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
        String rootPath = getPathFor("/st/htmlcomponent");
        StringTemplateGroup group = factory.createGroupFromRootPath(rootPath, errorListener);

        StringTemplate manyComponentsTemplate = group.getInstanceOf("manyComponents");

        log.info("Found template [" + manyComponentsTemplate.getName() + "]");

        String representation = templateRenderer.render(manyComponentsTemplate);
        log.info("Representation:\n[[<<" + representation + ">>]]");

        StringTemplate template = manyComponentsTemplate.getDOTForDependencyGraph(true);
        String dotDependencyGraph = templateRenderer.render(template);
        log.info("DOT dependencyGraph: \n" + dotDependencyGraph);

        log.info("Dependencies And Parameters of Template:\n" + printDependenciesAndParametersOf(manyComponentsTemplate));
    }

    private String getPathFor(String templatePath) {
        String rootPath = getStringTemplateRootDir() + templatePath;
        return rootPath;
    }

    private static String printDependenciesAndParametersOf(StringTemplate template) {
        Map<Object, Object> edges = new HashMap<Object, Object>();
        template.getDependencyGraph(edges, true);
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Object, Object> edge : edges.entrySet()) {

            sb.append("edge: key=").append(edge.getKey()).append("#").append(edge.getKey().getClass().getSimpleName());
            sb.append(", ").append("value=").append(edge.getValue()).append("#").append(edge.getValue().getClass().getSimpleName()).append("\n");
        }
        return sb.toString();
    }
}