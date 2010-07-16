package com.jimbarritt.spikes.stringtemplate;

import com.jimbarritt.spikes.stringtemplate.io.*;
import org.antlr.stringtemplate.*;
import org.antlr.stringtemplate.language.*;
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

        templateRenderer.render(manyComponentsTemplate);

        log.info("ManyComponents:\n" + printTemplateArguments("    -->", manyComponentsTemplate));
        log.info("Dependencies And Parameters of Template:\n" + printDependenciesOf(manyComponentsTemplate));
    }


    private String getPathFor(String templatePath) {
        return getStringTemplateRootDir() + templatePath;        
    }

    @SuppressWarnings("unchecked")
    private static String printDependenciesOf(StringTemplate template) {
        Map<String, Set> edges = new HashMap<String, Set>();
        template.getDependencyGraph(edges, true);
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Set> edge : edges.entrySet()) {
            String edgeName = edge.getKey();
            Set<String> elements = edge.getValue();
            sb.append("[").append(edgeName).append("]:\n");
            for (String includedTemplateName : elements) {
                sb.append("    ").append(includedTemplateName).append("\n");
                sb.append(printTemplateArguments("     -->", template.getGroup(), includedTemplateName));
            }            
        }
        return sb.toString();
    }

    private static String printTemplateArguments(String indent, StringTemplateGroup group, String includedTemplateName) {
        StringTemplate stringTemplate = group.getInstanceOf(includedTemplateName);
        return printTemplateArguments(indent, stringTemplate);
    }

    private static String printTemplateArguments(String indent, StringTemplate stringTemplate) {
        StringBuilder sb = new StringBuilder();

        sb.append(indent).append("Attributes: ").append(stringTemplate.getAttributes()).append("\n");
        appendArgumentContext(indent, stringTemplate, sb);
        appendArgumentAst(indent, stringTemplate, sb);
        appendFormalArguments(indent, stringTemplate, sb);

        return sb.toString();
    }

    private static void appendArgumentContext(String indent, StringTemplate stringTemplate, StringBuilder sb) {
        sb.append(indent).append("ArgumentContext:").append(stringTemplate.getArgumentContext()).append("\n");
    }

    private static void appendArgumentAst(String indent, StringTemplate stringTemplate, StringBuilder sb) {
        StringTemplateAST ast = stringTemplate.getArgumentsAST();
        sb.append(indent).append("Argument AST:\n");
        String astTree = (ast == null) ? "NO Argument AST" : ast.toStringTree();
        sb.append(indent).append(astTree).append("\n");
    }

    @SuppressWarnings("unchecked")
    private static void appendFormalArguments(String indent, StringTemplate stringTemplate, StringBuilder sb) {
        Map<Object, Object> formalArguments = stringTemplate.getFormalArguments();
        sb.append(indent).append("Formal Arguments (").append(formalArguments.size()).append("):\n");
        for (Map.Entry<Object, Object> entry : formalArguments.entrySet()) {
            sb.append(indent);
            sb.append("key   : ").append(entry.getKey()).append(", ").append("\n");
            sb.append(indent);
            sb.append("value : ").append(entry.getValue()).append(valueType(entry.getValue())).append("\n");
        }
    }

    private static String valueType(Object value) {
        return (value == null)
                ? "#NULL"
                : "#" + value.getClass().getSimpleName();
    }
}