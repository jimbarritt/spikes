package com.jimbarritt.spikes.stringtemplate.reflect;

import antlr.collections.*;
import org.antlr.stringtemplate.*;
import org.antlr.stringtemplate.language.*;
import org.apache.log4j.*;

import java.util.*;
import java.util.List;

import static java.lang.String.format;

public class StringTemplateIntrospector {
    private static final Logger log = Logger.getLogger(StringTemplateIntrospector.class);

    public static StringTemplateDefinition inspect(StringTemplate stringTemplate) {
        return new StringTemplateIntrospector().createStringTemplateDefinition(stringTemplate);
    }

    private StringTemplateDefinition createStringTemplateDefinition(StringTemplate stringTemplate) {
        List<StringTemplateInclude> invokedTemplates = parseIncludesTemplates(stringTemplate);
        List<StringTemplateAttribute> referencedAttributes = parseReferencedAttributes(stringTemplate);
        return new StringTemplateDefinition(stringTemplate.getName(), invokedTemplates, referencedAttributes);
    }


    private List<StringTemplateInclude> parseIncludesTemplates(StringTemplate stringTemplate) {
        IncludeChunkMatcher includeChunkMatcher = new IncludeChunkMatcher(new IncludeAstParser());        
        for (Object chunk : stringTemplate.getChunks()) {
            includeChunkMatcher.matchChunk(chunk);
        }
        return includeChunkMatcher.includedTemplates();
    }

    private static class IncludeChunkMatcher {
        List<StringTemplateInclude> includes = new ArrayList<StringTemplateInclude>();
        private final IncludeAstParser parser;

        public IncludeChunkMatcher(IncludeAstParser parser) {
            this.parser = parser;
        }

        public void matchChunk(Object chunk) {
            if (!(chunk instanceof ASTExpr)) {
                return;
            }

            ASTExpr astExpr = (ASTExpr) chunk;
            if (isInclude(astExpr)) {
                includes.add(parser.parseIncludeFrom(astExpr.getAST()));
            }
        }

        public List<StringTemplateInclude> includedTemplates() {
            return includes;
        }

        private static boolean isInclude(ASTExpr astExpr) {            
            return "include".equals(astExpr.getAST().getText());
        }
    }

    private static class IncludeAstParser {

        private StringTemplateInclude parseIncludeFrom(AST includeAst) {
            try {
                StringTemplateDefinition definition = new StringTemplateDefinition(includeAst.getFirstChild().getText());

                StringTemplateAST stringTemplateAST = convertToStringTemplateAst(includeAst.getFirstChild().getNextSibling());

                List<StringTemplateArgument> arguments = parseArgumentsFrom(stringTemplateAST);
                log.debug("Included string template is: " + stringTemplateAST.getStringTemplate());
                return new StringTemplateInclude(definition, arguments);
            } catch (Exception e) {
                throw new StringTemplateIntrospectionException("Failed to parse include from ast (See Cause). AST: " + includeAst.toStringTree(), e);
            }
        }

        private StringTemplateAST convertToStringTemplateAst(AST ast) {
            if (!(ast instanceof StringTemplateAST)) {
                throw new StringTemplateIntrospectionException(format("ast [%s] is not a StringTemplateAST, it is a [%s]", ast, ast.getClass().getSimpleName()));
            }
            return (StringTemplateAST)ast;            
        }

        private List<StringTemplateArgument> parseArgumentsFrom(StringTemplateAST argsAst) {
            if (!"ARGS".equals(argsAst.getText())) {
                throw new StringTemplateIntrospectionException("Tried to parse arguments from an AST which is not called 'ARGS'");
            }

            if (0 == argsAst.getNumberOfChildren()) {
                return new ArrayList<StringTemplateArgument>();
            }

            List<StringTemplateArgument> arguments = new ArrayList<StringTemplateArgument>();
            AST firstArgumentAst = argsAst.getFirstChild();
            arguments.add(parseArgumentFrom(firstArgumentAst));

            AST nextArgumentAst = firstArgumentAst.getNextSibling();
            while (nextArgumentAst != null) {
                arguments.add(parseArgumentFrom(nextArgumentAst));
                nextArgumentAst = nextArgumentAst.getNextSibling();
            }

            return arguments;
        }

        private StringTemplateArgument parseArgumentFrom(AST argumentAst) {
            if (!"=".equals(argumentAst.getText())) {
                throw new StringTemplateIntrospectionException("Tried to parse arguments from first child of AST which is not called '='");
            }
            if (2 != argumentAst.getNumberOfChildren()) {
                throw new StringTemplateIntrospectionException("Argument AST does not have 2 children, instead it has " + argumentAst.getNumberOfChildren());
            }

            String argumentName = argumentAst.getFirstChild().getText();
            String argumentValue = argumentAst.getFirstChild().getNextSibling().getText();

            return new StringTemplateArgument(argumentName, argumentValue);
        }
    }

    private List<StringTemplateAttribute> parseReferencedAttributes(StringTemplate stringTemplate) {
        AttributeChunkMatcher attributeChunkMatcher = new AttributeChunkMatcher(new AttributeAstParser());
        for (Object chunk : stringTemplate.getChunks()) {
            attributeChunkMatcher.matchChunk(chunk);
        }
        return attributeChunkMatcher.referencedAttributes();
    }

    private static class AttributeChunkMatcher  {
        List<StringTemplateAttribute> referencedAttributes = new ArrayList<StringTemplateAttribute>();
        private final AttributeAstParser parser;

        public AttributeChunkMatcher(AttributeAstParser parser) {
            this.parser = parser;
        }

        public void matchChunk(Object chunk) {
            if (!(chunk instanceof ASTExpr)) {
                return;
            }

            ASTExpr astExpr = (ASTExpr) chunk;
            if (isReferencedAttribute(astExpr)) {
                referencedAttributes.add(parser.parseAttributeFrom(astExpr.getAST()));
            }
        }

        public List<StringTemplateAttribute> referencedAttributes() {
            return referencedAttributes;
        }

        private static boolean isReferencedAttribute(ASTExpr astExpr) {           
            return (ActionParser.ID == astExpr.getAST().getType());            
        }
    }

    private static class AttributeAstParser {

        public StringTemplateAttribute parseAttributeFrom(AST ast) {
            return new StringTemplateAttribute(ast.getText());
        }
    }

}