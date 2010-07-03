package com.jimbarritt.spikes.stringtemplate;

import com.jimbarritt.spikes.stringtemplate.io.*;
import org.antlr.stringtemplate.*;
import org.apache.log4j.*;
import org.junit.*;

import java.io.*;

import static com.jimbarritt.spikes.stringtemplate.io.StringTemplateRootPath.getStringTemplateRootDir;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class StringTemplateInheritanceTest {

    private static final Logger log = Logger.getLogger(StringTemplateInheritanceTest.class);

    @Test
    public void templateImplementsAnInterface() {
        String searchPath = getStringTemplateRootDir() + "/st/inheritance";
        log.info("Adding search path: " + searchPath);

        File f = new File(searchPath + "/page.sti");
        assertThat(f.exists(), is(true));
        StringTemplateGroup.registerGroupLoader(new PathGroupLoader(searchPath, createStringTemplateErrorListener()));
        
        StringTemplateGroup group = new StringTemplateLoader().loadGroupFromClasspath("st/inheritance/pageBase.stg");
        StringTemplate template = group.getInstanceOf("page");
        template.setAttribute("title", "Some page");        

        String result = new StringTemplateRenderer().render(template);

        log.info("Result\n" + result);
    }

    private StringTemplateErrorListener createStringTemplateErrorListener() {

        return new StringTemplateErrorListener() {

            @Override public void error(String msg, Throwable e) {
                log.error(msg, e);
            }

            @Override public void warning(String msg) {
                log.warn(msg);
            }
        };
    }

}