package com.jimbarritt.spikes.restfulie.functionaltest;

import com.jimbarritt.spikes.restfulie.io.*;
import org.apache.log4j.*;
import org.junit.*;

import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.containsString;

public class SimpleHtmlTest {

    private static final Logger log = Logger.getLogger(SimpleHtmlTest.class);

    @Test
    public void canGetALocation() throws Exception {
        SimpleHttpClient httpClient = new SimpleHttpClient();

        String representation = httpClient.getFrom("http://localhost:8080/restfulie-spike/simplehtml/34523");
        
        assertThat(representation, containsString("34523"));
    }
}