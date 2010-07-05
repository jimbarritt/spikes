package com.jimbarritt.spikes.stringtemplate.io;

import org.antlr.stringtemplate.*;
import org.antlr.stringtemplate.language.*;

import java.io.*;

class InterfaceBasedStringTemplateGroup extends StringTemplateGroup {
    public InterfaceBasedStringTemplateGroup(String name, String rootDir) {
        super(name, rootDir);
    }

    public InterfaceBasedStringTemplateGroup(Reader reader, StringTemplateErrorListener errorListener) {
        super(reader, DefaultTemplateLexer.class , errorListener);
    }

    @Override public void implementInterface(String interfaceName) {
        super.implementInterface(interfaceName);
        super.verifyInterfaceImplementations();
    }
}