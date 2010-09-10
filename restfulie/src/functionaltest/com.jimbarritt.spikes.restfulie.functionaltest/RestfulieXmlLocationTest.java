package com.jimbarritt.spikes.restfulie.functionaltest;

import br.com.caelum.restfulie.*;
import br.com.caelum.restfulie.http.*;
import br.com.caelum.restfulie.mediatype.*;
import com.jimbarritt.spikes.restfulie.client.*;
import com.jimbarritt.spikes.restfulie.logging.*;
import com.thoughtworks.xstream.*;
import org.apache.log4j.*;
import org.junit.*;

import java.util.*;

import static br.com.caelum.restfulie.Restfulie.resource;
import static com.jimbarritt.spikes.restfulie.logging.StringFormatLogger.getStringFormatLogger;
import static java.lang.String.format;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.*;
import static org.junit.matchers.JUnitMatchers.containsString;

public class RestfulieXmlLocationTest {
    private static final StringFormatLogger log = getStringFormatLogger(RestfulieXmlLocationTest.class);

    private RestClient restfulie;
    private RemoteApplication remoteApplication;

    @Before
	public void setUp() throws Exception {
		restfulie = Restfulie.custom();		
        restfulie.getMediaTypes().register(new XmlMediaType().withTypes(Location.class));
        remoteApplication = new RemoteApplication();
	}

	@Test
	public void canGetALocationRepresentation() throws Exception {
        Response response = restfulie.at(remoteApplication.uriForPath("/locations/666"))
                                     .accept("application/xml")
                                     .get();
        log.info(format("Response was \n%s", response.getContent()));
		Location location = response.getResource();

		assertNotNull(location);
        assertThat(location.toString(), containsString("666"));

        log.info(format("Current Location : %s", location));

	}

    @Test    
	public void canNavigateRelations() throws Exception {
        Response response = restfulie.at(remoteApplication.uriForPath("/locations/666"))
                                     .accept("application/xml")
                                     .get();
        log.info(format("Response was \n%s", response.getContent()));
		Location location = response.getResource();
        assertThat(location, is(not(nullValue())));
        Resource resource = resource(location);
        log.info("Links: %s", resource.getLinks("exit"));
        Location nextLocation = resource.getLinks("exit").get(0).follow().access().getResource();
        assertNotNull(nextLocation);
        assertThat(nextLocation.toString(), containsString("33"));

        log.info(format("Next Location : %s", nextLocation));

	}
	
}