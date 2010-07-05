package com.jimbarritt.spikes.stringtemplate;

import com.jimbarritt.spikes.stringtemplate.io.*;
import org.antlr.stringtemplate.*;
import org.apache.log4j.*;
import org.junit.*;

import java.io.*;
import java.util.*;

import static com.jimbarritt.spikes.stringtemplate.io.StringTemplateRootPath.*;
import static org.hamcrest.core.Is.*;
import static org.junit.Assert.*;
import static org.junit.matchers.JUnitMatchers.*;

public class StringTemplateInheritanceTest {

    private static final Logger log = Logger.getLogger(StringTemplateInheritanceTest.class);

    @Test
    public void templateImplementsAnInterface() {
        String searchPath = getStringTemplateRootDir() + "/st/inheritance";
        log.info("Adding search path: " + searchPath);

        File f = new File(searchPath + "/page.sti");
        assertThat(f.exists(), is(true));
        StringTemplateGroup.registerGroupLoader(new PathGroupLoader(searchPath, new Log4jStringTemplateErrorListener()));

        StringTemplateGroup group = new StringTemplateLoader().loadGroupFromClasspath("st/inheritance/pageBase.stg");
        StringTemplate template = group.getInstanceOf("page");
        template.setAttribute("title", "Some page");

        String result = new StringTemplateRenderer().render(template);

        log.info("Result:[[\n" + result + "]]");
        assertThat(result, containsString("<title>Some page</title>"));
    }

    @Test
    public void templateFailsIfYouDontImplementInterface() {
        Log4jStringTemplateErrorListener errorListener = new Log4jStringTemplateErrorListener();
        StringTemplateGroup.registerGroupLoader(new PathGroupLoader(getStringTemplateRootDir() + "/st/inheritance", errorListener));

        new StringTemplateLoader(errorListener).loadGroupFromClasspath("st/inheritance/brokenPage.stg");

        assertThat(errorListener.getErrors().size(), is(3));
    }

    private static class Log4jStringTemplateErrorListener implements StringTemplateErrorListener {
        private final List<String> errors = new ArrayList<String>();

        public Log4jStringTemplateErrorListener() {
        }

        @Override public void error(String msg, Throwable e) {
            log.error(msg, e);
        }

        @Override public void warning(String msg) {
            log.warn(msg);
        }

        public List<String> getErrors() {
            return errors;
        }
    }

}