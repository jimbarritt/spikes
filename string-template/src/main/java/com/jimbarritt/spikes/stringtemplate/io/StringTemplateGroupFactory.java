package com.jimbarritt.spikes.stringtemplate.io;

import org.antlr.stringtemplate.*;
import org.antlr.stringtemplate.language.*;

import java.io.*;

public interface StringTemplateGroupFactory {

    StringTemplateGroup createGroupFromRootPath(String name, String rootPath, StringTemplateErrorListener errorListener);

    StringTemplateGroup createGroupFromReader(Reader reader, StringTemplateErrorListener errorListener);

}