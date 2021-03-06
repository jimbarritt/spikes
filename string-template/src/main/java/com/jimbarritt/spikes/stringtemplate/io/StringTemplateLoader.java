package com.jimbarritt.spikes.stringtemplate.io;

import com.jimbarritt.spikes.stringtemplate.*;
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

    private final StringTemplateGroupFactory groupFactory;
    private final StringTemplateErrorListener errorListener;

    public StringTemplateLoader() {
        this(new StandardStringTemplateGroupFactory(), StringTemplateGroup.DEFAULT_ERROR_LISTENER);
    }

    public StringTemplateLoader(Log4jStringTemplateErrorListener errorListener) {
        this(new StandardStringTemplateGroupFactory(), errorListener);
    }

    public StringTemplateLoader(StringTemplateGroupFactory groupFactory, StringTemplateErrorListener errorListener) {
        this.groupFactory = groupFactory;
        this.errorListener = errorListener;
    }

    public StringTemplateGroup loadGroupFromClasspath(String groupPath) {
        SafeReader safeReader = nullSafeReader();
        try {
            URL groupUrl = currentThread().getContextClassLoader().getResource(groupPath);
            if (groupUrl == null) {
                throw new StringTemplateException("Could not load resource from classpath: " + groupPath);
            }
            safeReader = new SafeReader(new InputStreamReader(groupUrl.openStream()));
            return groupFactory.createGroupFromReader(safeReader, errorListener);
        } catch (Exception e) {
            throw new StringTemplateException("Could not load template", e);
        } finally {
            safeReader.tryToClose();
        }

    }

    public StringTemplateGroup loadGroupFromRootPath(String rootPath) {
        return groupFactory.createGroupFromRootPath(rootPath, errorListener);
    }

    @SuppressWarnings("unchecked")
    public static void mergeGroups(StringTemplateGroup groupToAdd, StringTemplateGroup groupToAddTo) {
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
