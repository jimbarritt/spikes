package com.jimbarritt.spikes.restfulie.functionaltest;

import com.jimbarritt.spikes.restfulie.io.*;
import org.apache.log4j.*;
import org.junit.*;

public class LocationTest {

    private static final Logger log = Logger.getLogger(LocationTest.class);

    @Test
    public void canGetALocation() throws Exception {
        SimpleHttpClient httpClient = new SimpleHttpClient();

        String representation = httpClient.getFrom("http://localhost:8080/restfulie-spike/locations/34523");
        
        log.info("Content: " + representation);
    }
}