package com.jimbarritt.spikes.stringtemplate;

import org.antlr.stringtemplate.*;
import org.apache.log4j.*;
import org.junit.*;

import java.io.*;

import static org.hamcrest.core.Is.*;
import static org.hamcrest.core.IsNull.*;
import static org.junit.Assert.*;
import static org.junit.matchers.JUnitMatchers.containsString;

public class StringTemplateGroupTest {

    private static final Logger log = Logger.getLogger(StringTemplateGroupTest.class);

    @Test
    public void loadAStringTemplateGroup() throws Exception {
        StringTemplateGroup group = new StringTemplateClasspathLoader().loadGroupFromFile("st/solarsystem/simpleGroup.stg");
        StringTemplate template = group.getInstanceOf("outerTemplate");
        template.setAttribute("input", "Hello World");

        String result = new StringTemplateRenderer().render(template);

        log.info("Result\n" + result);
    }

    @Test
    public void loadsTemplatesFromAHierarchyOfGroups() {
        StringTemplateGroup csvGroup = new StringTemplateClasspathLoader().loadGroupFromFile("st/formatting/csv.stg");
        StringTemplateGroup utilityGroup = new StringTemplateClasspathLoader().loadGroupFromFile("st/solarsystem/simpleGroup.stg");
        StringTemplateGroup coreTemplateGroup = new StringTemplateGroup("coreTemplates", getStringTemplateRootDir());

        utilityGroup.setSuperGroup(csvGroup);
        coreTemplateGroup.setSuperGroup(utilityGroup);

        StringTemplate usesSimpleGroupTemplate = coreTemplateGroup.getInstanceOf("st/solarsystem/usesSimpleGroupTemplate");
        usesSimpleGroupTemplate.setAttribute("input", "jim");

        String result = new StringTemplateRenderer().render(usesSimpleGroupTemplate);
        log.info("Result:\n" + result);
        assertThat(result, containsString("nestedInput = jim"));
        assertThat(result, containsString("CSVROW: jim"));
    }

    @Test
    public void loadsTemplatesFromARootDirectory() throws Exception {
        StringTemplateGroup group = new StringTemplateGroup("coreTemplates", getStringTemplateRootDir());

        StringTemplate listOfPlanetsTemplate = group.getInstanceOf("st/solarsystem/listofplanets");
        assertThat(listOfPlanetsTemplate, is(notNullValue()));
    }

    private static String getStringTemplateRootDir() {
        String templateGroupRootPath = new File("src/main/resource/").getAbsolutePath();
        File f = new File(templateGroupRootPath, "st/solarsystem/listofplanets.st");
        assertThat(f.getAbsolutePath(), f.exists(), is(true));

        log.info("Loading templates from: " + templateGroupRootPath);
        return templateGroupRootPath;
    }

    @Test
    public void loadsTemplatesFromTheClasspath() throws Exception {
        StringTemplateGroup group = new StringTemplateGroup("coreTemplates");

        StringTemplate listOfPlanetsTemplate = group.getInstanceOf("st/solarsystem/listofplanets");
        assertThat(listOfPlanetsTemplate, is(notNullValue()));
    }
}
