package com.jimbarritt.spikes.restfulie.functionaltest;

import com.jimbarritt.spikes.restfulie.io.http.*;
import org.junit.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.containsString;


public class SimpleHttpClientBasedTest {
    private RemoteApplication remoteApplication;

    @Before
    public void setup() {
        remoteApplication = new RemoteApplication();
    }

    @Test
    public void canGetALocation() throws Exception {
        HttpClient httpClient = new HttpClient();

        String representation = httpClient.getFrom(remoteApplication.uriForPath("/simplehtml/34523"));
        
        assertThat(representation, containsString("34523"));
    }

    @Test
    public void canMakeAPost() {
        HttpClient httpClient = new HttpClient();


        remoteApplication = new RemoteApplication();
        HttpResponse response = httpClient.withRequestParameter("parameterA", "http://foobar/A")
                                            .withRequestParameter("parameterB", "http://foobar/B")
                                            .postTo(remoteApplication.uriForPath("/simplehtml/3456"));

        assertThat(response.responseCode(), is(201));
    }

    @Test
    public void canMakeAPut() {
        HttpClient httpClient = new HttpClient();


        remoteApplication = new RemoteApplication();
        HttpResponse response = httpClient.withRequestParameter("parameterA", "http://foobar/A")
                                            .withRequestParameter("parameterB", "http://foobar/B")
                                            .putTo(remoteApplication.uriForPath("/simplehtml/3456"));

        assertThat(response.responseCode(), is(200));
    }
}