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
        HttpConsumer httpConsumer = new HttpConsumer();

        String representation = httpConsumer.getFrom(remoteApplication.uriForPath("/simplehtml/34523"));
        
        assertThat(representation, containsString("34523"));
    }

    @Test
    public void canMakeAPost() {
        HttpConsumer httpConsumer = new HttpConsumer();


        remoteApplication = new RemoteApplication();
        HttpResponse response = httpConsumer.withRequestParameter("parameterA", "http://foobar/A")
                                            .withRequestParameter("parameterB", "http://foobar/B")
                                            .postTo(remoteApplication.uriForPath("/simplehtml/3456"));

        assertThat(response.responseCode(), is(201));
    }

    @Test
    public void canMakeAPut() {
        HttpConsumer httpConsumer = new HttpConsumer();


        remoteApplication = new RemoteApplication();
        HttpResponse response = httpConsumer.withRequestParameter("parameterA", "http://foobar/A")
                                            .withRequestParameter("parameterB", "http://foobar/B")
                                            .putTo(remoteApplication.uriForPath("/simplehtml/3456"));

        assertThat(response.responseCode(), is(200));
    }
}