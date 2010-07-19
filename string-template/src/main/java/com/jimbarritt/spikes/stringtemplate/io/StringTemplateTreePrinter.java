package com.jimbarritt.spikes.stringtemplate.io;

import antlr.collections.*;
import org.antlr.stringtemplate.*;
import org.antlr.stringtemplate.language.*;

import java.util.*;

public class StringTemplateTreePrinter {

    public static final String INDENT = "    ";

    @SuppressWarnings("unchecked")
    public String printTreeOf(StringTemplate template) {
        Map<String, Set> edges = new HashMap<String, Set>();
        template.getDependencyGraph(edges, true);
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Set> edge : edges.entrySet()) {
            String edgeName = edge.getKey();
            Set<String> elements = edge.getValue();
            sb.append("[").append(edgeName).append("]:\n");
            for (String includedTemplateName : elements) {
                sb.append(INDENT).append(includedTemplateName).append("\n");
                sb.append(printTemplateArguments(INDENT + "-->", template.getGroup(), includedTemplateName));
            }
        }
        return sb.toString();
    }

    public String printTemplateArguments(String indent, StringTemplate stringTemplate) {
        StringBuilder sb = new StringBuilder();

        appendAttributes(indent, stringTemplate, sb);
        appendArgumentContext(indent, stringTemplate, sb);
        appendArgumentAst(indent, stringTemplate, sb);
        appendFormalArguments(indent, stringTemplate, sb);
        appendChunks(indent, stringTemplate, sb);

        return sb.toString();
    }

    private String printTemplateArguments(String indent, StringTemplateGroup group, String includedTemplateName) {
        StringTemplate stringTemplate = group.getInstanceOf(includedTemplateName);
        return printTemplateArguments(indent, stringTemplate);
    }

    private static void appendChunks(String indent, StringTemplate stringTemplate, StringBuilder sb) {
        for (Object chunk : stringTemplate.getChunks()) {
            if (chunk instanceof ASTExpr) {
                ASTExpr astExpr = (ASTExpr)chunk;
                appendAstExpr(indent, sb, astExpr);
            }
        }

    }

    private static void appendAstExpr(String indent, StringBuilder sb, ASTExpr astExpr) {
        sb.append(indent).append(" Chunk: ").append(astExpr).append(typeOf(astExpr)).append("\n");
        appendAst(indent, sb, astExpr.getAST());
    }

    private static void appendAst(String indent, StringBuilder sb, AST ast) {

        AST firstChild = ast.getFirstChild();
        if (firstChild != null) {
            sb.append(INDENT).append(indent).append(" Number of children: ").append(ast.getNumberOfChildren()).append("\n");
            sb.append(INDENT).append(indent).append(" First child: ").append(firstChild).append("\n");
            appendAst(INDENT + INDENT + indent, sb, firstChild);
            AST nextSibling = firstChild.getNextSibling();
            if (nextSibling != null) {
                sb.append(INDENT).append(indent).append(" Next Child : ").append(nextSibling).append(typeOf(nextSibling)).append("\n");
                appendAst(INDENT + indent, sb, nextSibling);
            }
        }
    }

    private static void appendAttributes(String indent, StringTemplate stringTemplate, StringBuilder sb) {
        sb.append(indent).append("Attributes: ").append(stringTemplate.getAttributes()).append("\n");
    }

    private static void appendArgumentContext(String indent, StringTemplate stringTemplate, StringBuilder sb) {
        sb.append(indent).append("ArgumentContext: ").append(stringTemplate.getArgumentContext()).append("\n");
    }

    private static void appendArgumentAst(String indent, StringTemplate stringTemplate, StringBuilder sb) {
        StringTemplateAST ast = stringTemplate.getArgumentsAST();
        sb.append(indent).append("Argument AST: ");
        String astTree = (ast == null) ? "NO Argument AST" : ast.toStringTree();
        sb.append(astTree).append("\n");
    }

    @SuppressWarnings("unchecked")
    private static void appendFormalArguments(String indent, StringTemplate stringTemplate, StringBuilder sb) {
        Map<Object, Object> formalArguments = stringTemplate.getFormalArguments();
        sb.append(indent).append("Formal Arguments (").append(formalArguments.size()).append("):\n");
        for (Map.Entry<Object, Object> entry : formalArguments.entrySet()) {
            sb.append(indent);
            sb.append("key   : ").append(entry.getKey()).append(", ").append("\n");
            sb.append(indent);
            sb.append("value : ").append(entry.getValue()).append(typeOf(entry.getValue())).append("\n");
        }
    }

    private static String typeOf(Object value) {
        return (value == null)
                ? "#NULL"
                : "#" + value.getClass().getSimpleName();
    }

}