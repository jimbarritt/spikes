package com.jimbarritt.spikes.restfulie.functionaltest;

import br.com.caelum.restfulie.*;
import br.com.caelum.restfulie.mediatype.*;
import com.jimbarritt.spikes.restfulie.client.domain.*;
import com.jimbarritt.spikes.restfulie.logging.*;
import org.junit.*;

import static br.com.caelum.restfulie.Restfulie.resource;
import static com.jimbarritt.spikes.restfulie.logging.StringFormatLogger.getStringFormatLogger;
import static java.lang.String.format;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.*;
import static org.junit.matchers.JUnitMatchers.containsString;

public class XmlLocationTest {
    private static final StringFormatLogger log = getStringFormatLogger(XmlLocationTest.class);

    private RestClient restfulie;
    private RemoteApplication remoteApplication;

    @Before
	public void setUp() throws Exception {
		restfulie = Restfulie.custom();		
        restfulie.getMediaTypes().register(new XmlMediaType()
                                                    .withTypes(Location.class)
                                                    .withTypes(EncounterRequest.class));
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
    public void canPostAnEncounterRequestToALocation() throws Exception {
        EncounterRequest encounterRequest = new EncounterRequest()
                                                    .withCharacterUrl("foo")
                                                    .withCreatureUrl("baa");

        Response response = restfulie.at(remoteApplication.uriForPath("/locations/666"))
                                             .as("application/xml")
                                             .post(encounterRequest);

        log.info(format("Response was (%d)\n%s", response.getCode(), response.getContent()));

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