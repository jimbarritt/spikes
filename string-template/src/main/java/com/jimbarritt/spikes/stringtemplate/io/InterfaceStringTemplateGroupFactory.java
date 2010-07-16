package com.jimbarritt.spikes.stringtemplate.io;

import org.antlr.stringtemplate.*;
import org.antlr.stringtemplate.language.*;

import java.io.*;

public class InterfaceStringTemplateGroupFactory implements StringTemplateGroupFactory {
    @Override
    public StringTemplateGroup createGroupFromRootPath(String rootPath, StringTemplateErrorListener errorListener) {
        StringTemplateGroup group = new InterfaceBasedStringTemplateGroup(rootPath);
        group.setErrorListener(errorListener);
        return group;
    }

    @Override
    public StringTemplateGroup createGroupFromReader(Reader reader, StringTemplateErrorListener errorListener) {
        return new InterfaceBasedStringTemplateGroup(reader, errorListener);
    }
}