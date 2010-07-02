package com.jimbarritt.spikes.stringtemplate;

import org.antlr.stringtemplate.*;
import org.antlr.stringtemplate.language.*;

import java.io.*;
import java.net.*;

import static com.jimbarritt.spikes.stringtemplate.io.NullReader.nullReader;

public class StringTemplateLoader {
    public StringTemplateGroup loadGroup(String groupPath) {
        Reader in = nullReader();
        try {
            URL groupUrl = Thread.currentThread().getContextClassLoader().getResource(groupPath);
            if (groupUrl == null) {
                throw new StringTemplateException("Could not load resource from classpath: " + groupPath);
            }
            in = new InputStreamReader(groupUrl.openStream());
            return new StringTemplateGroup(groupPath, DefaultTemplateLexer.class);
        } catch (Exception e) {
            throw new StringTemplateException("Could not load template", e);
        } finally {
            tryToClose(in);
        }

    }

    private static void tryToClose(Reader in) {
        try {
            in.close();
        }  catch (IOException e) {
            throw new StringTemplateException("Could not close reader.", e);
        }
    }
}
