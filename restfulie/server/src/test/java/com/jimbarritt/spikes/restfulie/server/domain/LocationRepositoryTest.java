package com.jimbarritt.spikes.restfulie.server.domain;

import com.jimbarritt.spikes.restfulie.io.*;
import com.jimbarritt.spikes.restfulie.logging.*;
import org.junit.*;

import java.util.*;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.containsString;

public class LocationRepositoryTest {
    private static final StringFormatLogger log = StringFormatLogger.getStringFormatLogger(LocationRepositoryTest.class);

    @Test
    public void loadUpLocationsFromClasspath() {

        LocationRepository locationRepository = new LocationRepository();

        Location location = locationRepository.get(1);
        String description = location.describe();
        log.info("Description \n%s", description);
        assertThat(description, containsString("Something smells fishy"));
        assertThat(description, containsString("(Go to 33)"));
        assertThat(description, containsString("(Go to 44)"));

        List<ExitTo> exitList = location.getExits();
        assertThat(exitList.size(), is(2));
        assertThat(exitList.get(0).number(), is(33));
        assertThat(exitList.get(1).number(), is(44));
    }

    // Dont forget to add the ?*.location pattern in your compiler resources!
    @Test
    public void testClasspathLoading() {
       String out = Iox.loadClasspathResourceAsString("locations/1.location");
        log.info("Output is %s", out);
    }

}