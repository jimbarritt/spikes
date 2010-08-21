package com.jimbarritt.spikes.restfulie.functionaltest;

import org.apache.log4j.*;
import org.junit.*;

import java.net.*;

public class LocationTest {

    private static final Logger log = Logger.getLogger(LocationTest.class);

    @Test
    public void canGetALocation() throws Exception {
        URL url = new URL("http://localhost:8080/restfulie-spike/");

        Object content = url.getContent();

        log.info("Content: " + content);
    }
}