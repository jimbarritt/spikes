package com.jimbarritt.spikes.stringtemplate.io;

import org.antlr.stringtemplate.*;
import org.apache.log4j.*;

import java.io.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class StringTemplateRootPath {
    private static final Logger log = Logger.getLogger(StringTemplateRootPath.class);
    
    public static StringTemplateGroup getProjectStringTemplates() {
        return new StringTemplateLoader().loadGroupFromRootPath("projectGroup", getStringTemplateRootDir());
    }

    public static String getStringTemplateRootDir() {
        String templateGroupRootPath = new File("src/main/resource/").getAbsolutePath();
        File f = new File(templateGroupRootPath, "st/solarsystem/listofplanets.st");
        assertThat(f.getAbsolutePath(), f.exists(), is(true));

        log.info("Loading templates from: " + templateGroupRootPath);
        return templateGroupRootPath;
    }
}