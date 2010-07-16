package com.jimbarritt.spikes.stringtemplate;

import com.jimbarritt.spikes.stringtemplate.io.*;
import org.antlr.stringtemplate.*;
import org.apache.log4j.*;
import org.junit.*;

import java.io.*;

import static com.jimbarritt.spikes.stringtemplate.io.StringTemplateRootPath.getStringTemplateRootDir;
import static org.antlr.stringtemplate.StringTemplateGroup.DEFAULT_ERROR_LISTENER;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class IntrospectTemplatesTest {

    private static final Logger log = Logger.getLogger(IntrospectTemplatesTest.class);
    private Log4jStringTemplateErrorListener errorListener;

    @Before
    public void onceBeforeEachTest() {
        errorListener = new Log4jStringTemplateErrorListener();
    }

    @Test
	public void canFindOutAboutTemplates() throws IOException {
        StringTemplateGroupFactory factory = new StandardStringTemplateGroupFactory();

        String rootPath = getStringTemplateRootDir() + "/st/htmlcomponent";
        StringTemplateGroup group = factory.createGroupFromRootPath("st/htmlcomponent", rootPath, errorListener);

        StringTemplate manyComponentsTemplate = group.getInstanceOf("manyComponents");

        log.info("Found template [" + manyComponentsTemplate.getName() + "]");

        String representation = new StringTemplateRenderer().render(manyComponentsTemplate);
        log.info("Representation:\n[[<<" + representation + ">>]]");
	}
}