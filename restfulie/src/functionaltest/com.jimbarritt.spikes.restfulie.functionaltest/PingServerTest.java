package com.jimbarritt.spikes.restfulie.functionaltest;

import com.jimbarritt.spikes.restfulie.io.http.*;
import org.junit.*;

import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.containsString;

public class PingServerTest {

    @Test
    public void hasWelcomePage() {
        HttpClient httpClient = new HttpClient();

        String representation = httpClient.getFrom(new RemoteApplication().rootUri());

        assertThat(representation, containsString("Welcome to the Restfulie Spike!"));
    }
}