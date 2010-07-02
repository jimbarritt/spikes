package com.jimbarritt.spikes.stringtemplate;

import com.jimbarritt.spikes.stringtemplate.io.*;
import org.antlr.stringtemplate.*;
import org.antlr.stringtemplate.language.*;
import org.apache.log4j.*;

import java.io.*;
import java.net.*;
import java.util.*;

import static com.jimbarritt.spikes.stringtemplate.io.NullReader.*;
import static java.lang.Thread.*;

public class StringTemplateLoader {

    private static final Logger log = Logger.getLogger(StringTemplateLoader.class);

    public StringTemplateGroup loadGroupFromClasspath(String groupPath) {
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

    public StringTemplateGroup loadGroupFromRootDir(String stringTemplateRootDir) {
        return new StringTemplateGroup("coreTemplates", stringTemplateRootDir);
    }

    @SuppressWarnings("unchecked")
    static void mergeGroups(StringTemplateGroup groupToAdd, StringTemplateGroup groupToAddTo) {
        Set<String> templateNames = (Set<String>) groupToAdd.getTemplateNames();
        for (String templateName : templateNames) {
            StringTemplate template = groupToAdd.getTemplateDefinition(templateName);
            log.debug("Adding Template: name=" + template.getName() + ", FormalArguments: " + template.getFormalArguments() + " Content: [[\n" + template.getTemplate() + "\n]]");

            StringTemplate newStringTemplate = groupToAddTo.defineTemplate(template.getName(), template.getTemplate());
            Map map = template.getFormalArguments();
            for (Object key : map.keySet()) {
                log.debug("Defining Formal Argument: [" + key + "], value: [" + map.get(key) + "]");
                newStringTemplate.defineFormalArgument((String) key);
            }

        }
    }
}
