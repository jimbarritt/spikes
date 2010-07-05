package com.jimbarritt.spikes.stringtemplate;

import com.jimbarritt.spikes.stringtemplate.io.*;
import org.antlr.stringtemplate.*;
import org.apache.log4j.*;
import org.junit.*;

import java.io.*;

import static com.jimbarritt.spikes.stringtemplate.io.StringTemplateRootPath.*;
import static org.hamcrest.core.Is.*;
import static org.junit.Assert.*;
import static org.junit.matchers.JUnitMatchers.*;

public class InheritanceTest {

    private static final Logger log = Logger.getLogger(InheritanceTest.class);
    private Log4jStringTemplateErrorListener errorListener;

    @Before
    public void onceBeforeEachTest() {
        errorListener = new Log4jStringTemplateErrorListener();
    }

    @Test
    public void templateGroupImplementsAnInterface() {
        String searchPath = getStringTemplateRootDir() + "/st/inheritance";
        log.info("Adding search path: " + searchPath);

        File f = new File(searchPath + "/Page.sti");
        assertThat(f.exists(), is(true));
        StringTemplateGroup.registerGroupLoader(new PathGroupLoader(searchPath, errorListener));

        StringTemplateGroup pageBase = new StringTemplateLoader(errorListener).loadGroupFromClasspath("st/inheritance/pageBase.stg");
        String pageBaseRepresentation = renderPage(pageBase);
        assertThat(pageBaseRepresentation, containsString("<title>Some page</title>"));
        assertThat(pageBaseRepresentation, containsString("<p>Here is a default body</p>"));

        StringTemplateGroup homePage = new StringTemplateLoader(errorListener).loadGroupFromClasspath("st/inheritance/homePage.stg");
        String homePageRepresentation = renderPage(homePage);
        assertThat(homePageRepresentation, containsString("<title>Some page</title>"));
        assertThat(homePageRepresentation, containsString("<p>Here is a Custom body</p>"));
    }

    private String renderPage(StringTemplateGroup pageBase) {
        StringTemplate template = pageBase.getInstanceOf("page");
        template.setAttribute("title", "Some page");
        String pageBaseRepresentation = new StringTemplateRenderer().render(template);
        log.info("Result:[[\n" + pageBaseRepresentation + "]]");
        return pageBaseRepresentation;
    }

    @Test
    public void templateGroupFailsToLoadIfYouDontImplementInterface() {
        StringTemplateGroup.registerGroupLoader(new PathGroupLoader(getStringTemplateRootDir() + "/st/inheritance", errorListener));

        new StringTemplateLoader(errorListener).loadGroupFromClasspath("st/inheritance/brokenPage.stg");

        assertThat(errorListener.errors().size(), is(2));
    }

    @Test
    public void canAssignAnInterfaceDynamically() {
        StringTemplateGroup.registerGroupLoader(new PathGroupLoader(getStringTemplateRootDir() + "/st/inheritance", errorListener));
        StringTemplateGroup group = new StringTemplateLoader().loadGroupFromClasspath("st/inheritance/pageBase.stg");
        group.implementInterface("page");
    }

}