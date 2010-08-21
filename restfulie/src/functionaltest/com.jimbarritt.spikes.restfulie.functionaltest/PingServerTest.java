package com.jimbarritt.spikes.restfulie.functionaltest;

import com.jimbarritt.spikes.restfulie.io.*;
import org.junit.*;

import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.containsString;

public class PingServerTest {

    @Test
    public void hasWelcomePage() {
        SimpleHttpClient httpClient = new SimpleHttpClient();

        String representation = httpClient.getFrom("http://localhost:8080/restfulie-spike/");

        assertThat(representation, containsString("Welcome to the Restfulie Spike!"));
    }
}