package com.jimbarritt.spikes.restfulie.functionaltest;

import br.com.caelum.restfulie.*;
import br.com.caelum.restfulie.mediatype.*;
import com.jimbarritt.spikes.restfulie.client.domain.*;
import org.apache.log4j.*;
import org.junit.*;

import java.util.*;

import static java.lang.String.format;
import static org.junit.Assert.*;
import static org.junit.matchers.JUnitMatchers.*;

public class JsonLocationTest {
    private static final Logger log = Logger.getLogger(JsonLocationTest.class);

    private RestClient restfulie;
    private RemoteApplication remoteApplication;

    @Before
	public void setUp() throws Exception {
		restfulie = Restfulie.custom();
		restfulie.getMediaTypes().register(new JsonMediaType() {
			@SuppressWarnings("unchecked")
			@Override
			protected List<Class> getTypesToEnhance() {
				return Arrays.<Class>asList(Location.class);
			}
		});
        remoteApplication = new RemoteApplication();
	}

	@Test
	public void canGetALocationRepresentation() throws Exception {
        Response response = restfulie.at(remoteApplication.uriForPath("/locations/1"))
                                     .accept("application/json")
                                     .get();
		Location location = response.getResource();

		assertNotNull(location);
        assertThat(location.toString(), containsString("666"));

        log.info(format("Current Location : %s", location));
        log.info(format("Response was \n%s", response.getContent()));
	}
	
}