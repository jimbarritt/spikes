package com.jimbarritt.spikes.stringtemplate;

import com.jimbarritt.spikes.stringtemplate.io.*;
import org.antlr.stringtemplate.*;
import org.junit.*;

import static com.jimbarritt.spikes.stringtemplate.io.StringTemplateRootPath.*;
import static org.hamcrest.core.Is.*;
import static org.junit.Assert.*;

public class WebsiteTest {
    private StringTemplateLoader templateLoader;
    private Log4jStringTemplateErrorListener errorListener;

    @Before
    public void onceBeforeEachTest() {
        errorListener = new Log4jStringTemplateErrorListener();
        templateLoader = new StringTemplateLoader(new InterfaceStringTemplateGroupFactory(), errorListener);
    }

    @Test
    public void rendersAPage() {
        StringTemplateGroup.registerGroupLoader(new PathGroupLoader(getStringTemplateRootDir() + "/st/htmlpages/site_definition", new Log4jStringTemplateErrorListener()));

        StringTemplateGroup siteDefinition = templateLoader.loadGroupFromRootPath(getStringTemplateRootDir() + "/st/htmlpages/site_definition");
        StringTemplateGroup siteA = templateLoader.loadGroupFromRootPath(getStringTemplateRootDir() + "/st/htmlpages/site_a");

        siteA.setSuperGroup(siteDefinition);
        siteA.implementInterface("website");


    }

    @Test
    public void failsIfSiteDoesntImplementInterface() {
        StringTemplateGroup.registerGroupLoader(new PathGroupLoader(getStringTemplateRootDir() + "/st/htmlpages/site_definition", new Log4jStringTemplateErrorListener()));

        StringTemplateGroup siteB = templateLoader.loadGroupFromRootPath(getStringTemplateRootDir() + "/st/htmlpages/site_b");
        siteB.implementInterface("website");

        assertThat(errorListener.errors().size(), is(1));
    }


}