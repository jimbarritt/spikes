package com.jimbarritt.spikes.stringtemplate.io;

import org.antlr.stringtemplate.*;
import org.antlr.stringtemplate.language.*;

import java.io.*;

public class StandardStringTemplateGroupFactory implements StringTemplateGroupFactory{
    @Override public StringTemplateGroup createGroupFromRootPath(String name, String rootPath, StringTemplateErrorListener errorListener) {
        StringTemplateGroup group = new StringTemplateGroup(name, rootPath, DefaultTemplateLexer.class);
        group.setErrorListener(errorListener);
        return group;
    }

    @Override
    public StringTemplateGroup createGroupFromReader(Reader reader, StringTemplateErrorListener errorListener) {
        return new StringTemplateGroup(reader, DefaultTemplateLexer.class, errorListener);
    }

}