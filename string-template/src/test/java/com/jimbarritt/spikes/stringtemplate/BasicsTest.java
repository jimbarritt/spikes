package com.jimbarritt.spikes.stringtemplate;

import java.io.IOException;
import java.net.URL;

import com.jimbarritt.spikes.stringtemplate.io.*;
import com.jimbarritt.spikes.stringtemplate.solarsystem.*;
import org.antlr.stringtemplate.StringTemplate;
import org.antlr.stringtemplate.StringTemplateGroup;
import org.apache.log4j.Logger;
import org.junit.*;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.containsString;


public class BasicsTest {
	private static final Logger log = Logger.getLogger(BasicsTest.class);

	@Test
	public void rendersASimpleTemplate() throws IOException {
		StringTemplate template = new StringTemplate("Hello Dolly, your name is [$personName$]");
		template.setAttribute("personName", "baaaa");

		String renderedContent = new StringTemplateRenderer().render(template);

		assertThat(renderedContent, is("Hello Dolly, your name is [baaaa]"));
	}



	@Test
	public void checksClasspathForTemplateDefinition() {
        String resourcePath = "st/formatting/lineBreak.st";
        log.info("Looking for " + resourcePath);

        URL resourceUrl = this.getClass().getClassLoader().getResource(resourcePath);
		assertThat("Resource [" + resourcePath + "] should be on the classpath.", resourceUrl, is(notNullValue()));
	}

	@Test
	public void loadsATemplateFromTheClasspath() throws IOException {
		StringTemplateGroup group = new StringTemplateGroup("testClasspathTemplateGroup");
		StringTemplate template = group.getInstanceOf("st/solarsystem/listofplanets");
		assertThat("Should be able to get the template definition.", template, is(notNullValue()));
	}


	@Test
	public void rendersAComplexTemplate() throws IOException {
		StringTemplateGroup group = new StringTemplateGroup("complexTemplateGroup");
		StringTemplate complexTemplate = group.getInstanceOf("st/solarsystem/listofplanets");
		TheSolarSystem solarSystem = new TheSolarSystem();
		solarSystem.setDescription("This is a description of the solar system");
		complexTemplate.setAttribute("theSolarSystem", solarSystem);
		String renderedString = new StringTemplateRenderer().render(complexTemplate);

		log.info("Rendered as:\n[" + renderedString + "]");
		assertThat(renderedString, containsString("The solar system is made up of " + solarSystem.getPlanets().size() + " planets"));

		assertThat(renderedString, containsString("This is a description of the solar system"));

	}
  
}
