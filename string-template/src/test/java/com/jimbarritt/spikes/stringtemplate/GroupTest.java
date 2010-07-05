package com.jimbarritt.spikes.stringtemplate;

import com.jimbarritt.spikes.stringtemplate.io.*;
import org.antlr.stringtemplate.*;
import org.apache.log4j.*;
import org.junit.*;

import java.util.*;

import static com.jimbarritt.spikes.stringtemplate.io.StringTemplateLoader.mergeGroups;
import static org.hamcrest.core.Is.*;
import static org.hamcrest.core.IsNull.*;
import static org.junit.Assert.*;
import static org.junit.matchers.JUnitMatchers.*;

/**
 * http://www.antlr.org/wiki/display/ST/Group+Files
 */
public class GroupTest {

    private static final Logger log = Logger.getLogger(GroupTest.class);

    @Test
    public void loadAStringTemplateGroup() throws Exception {
        StringTemplateGroup group = new StringTemplateLoader().loadGroupFromClasspath("st/solarsystem/simpleGroup.stg");
        StringTemplate template = group.getInstanceOf("outerTemplate");
        template.setAttribute("input", "Hello World");

        String result = new StringTemplateRenderer().render(template);

        log.info("Result\n" + result);
    }

    @Test
    public void loadsTemplatesFromAHierarchyOfGroups() {
        StringTemplateGroup csvGroup = new StringTemplateLoader().loadGroupFromClasspath("st/formatting/csv.stg");
        StringTemplateGroup utilityGroup = new StringTemplateLoader().loadGroupFromClasspath("st/solarsystem/simpleGroup.stg");
        StringTemplateGroup coreTemplateGroup = new StringTemplateGroup("coreTemplates", StringTemplateRootPath.getStringTemplateRootDir());

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
    @SuppressWarnings("unchecked")
    public void combinesTemplatesFromMultipleGroups() {
        StringTemplateLoader templateLoader = new StringTemplateLoader();
        StringTemplateGroup csvGroup = templateLoader.loadGroupFromClasspath("st/formatting/csv.stg");
        StringTemplateGroup utilityGroup = templateLoader.loadGroupFromClasspath("st/solarsystem/simpleGroup.stg");

        StringTemplateGroup coreTemplateGroup = templateLoader.loadGroupFromRootDir("allTemplates", StringTemplateRootPath.getStringTemplateRootDir());

        mergeGroups(csvGroup, coreTemplateGroup);
        mergeGroups(utilityGroup, coreTemplateGroup);

        StringTemplate usesSimpleGroupTemplate = coreTemplateGroup.getInstanceOf("st/solarsystem/usesSimpleGroupTemplate");
        usesSimpleGroupTemplate.setAttribute("input", "jim");

        String result = new StringTemplateRenderer().render(usesSimpleGroupTemplate);
        log.info("Result:\n" + result);

        Set<String> templateNames = (Set<String>) coreTemplateGroup.getTemplateNames();
        for (String templateName : templateNames) {
            log.debug("template: " + templateName);
        }


        assertThat(result, containsString("nestedInput = jim"));
        assertThat(result, containsString("CSVROW: jim"));

    }

    @Test
    public void loadsTemplatesFromARootDirectory() throws Exception {
        StringTemplateGroup group = new StringTemplateGroup("coreTemplates", StringTemplateRootPath.getStringTemplateRootDir());

        StringTemplate listOfPlanetsTemplate = group.getInstanceOf("st/solarsystem/listofplanets");
        assertThat(listOfPlanetsTemplate, is(notNullValue()));
    }

    @Test
    public void loadsTemplatesFromTheClasspath() throws Exception {
        StringTemplateGroup group = new StringTemplateGroup("coreTemplates");

        StringTemplate listOfPlanetsTemplate = group.getInstanceOf("st/solarsystem/listofplanets");
        assertThat(listOfPlanetsTemplate, is(notNullValue()));
    }
}
