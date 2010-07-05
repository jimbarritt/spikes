package com.jimbarritt.spikes.stringtemplate.htmlcomponent;

import com.jimbarritt.spikes.stringtemplate.io.*;
import org.antlr.stringtemplate.*;
import org.apache.log4j.*;
import org.junit.*;

import static com.jimbarritt.spikes.stringtemplate.io.StringTemplateRootPath.*;

public class HtmlPageWithComponentsTest {

    private static final Logger log = Logger.getLogger(HtmlPageWithComponentsTest.class);

    @Test
    public void hasAComponentOnThePage() {
        StringTemplateGroup group = getProjectStringTemplates();

        StringTemplate homepageTemplate = group.getInstanceOf("st/htmlcomponent/homepage");

        ComponentDefinition componentDefinition = new ComponentDefinition();

        componentDefinition.configureTemplate(homepageTemplate);

        String html = new StringTemplateRenderer().render(homepageTemplate);
        log.info("html: \n" + html);
    }
}