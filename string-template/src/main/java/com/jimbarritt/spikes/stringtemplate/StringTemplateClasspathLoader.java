package com.jimbarritt.spikes.stringtemplate;

import com.jimbarritt.spikes.stringtemplate.io.*;
import org.antlr.stringtemplate.*;
import org.antlr.stringtemplate.language.*;

import java.io.*;
import java.net.*;

import static com.jimbarritt.spikes.stringtemplate.io.NullReader.nullSafeReader;
import static java.lang.Thread.currentThread;

public class StringTemplateClasspathLoader {
    public StringTemplateGroup loadGroupFromFile(String groupPath) {
        SafeReader safeReader = nullSafeReader();
        try {
            URL groupUrl = currentThread().getContextClassLoader().getResource(groupPath);
            if (groupUrl == null) {
                throw new StringTemplateException("Could not load resource from classpath: " + groupPath);
            }
            safeReader = new SafeReader(new InputStreamReader(groupUrl.openStream()));
            return new StringTemplateGroup(safeReader, DefaultTemplateLexer.class);
        } catch (Exception e) {
            throw new StringTemplateException("Could not load template", e);
        } finally {
            safeReader.tryToClose();
        }

    }

}
