package com.jimbarritt.spikes.stringtemplate;

import org.junit.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SayHelloTest {

    @Test
    public void saysHelloToJim() {
        String response = new SayHello().sayHelloTo("Jim");

        assertThat(response, is("Hello Jim"));
    }

    @Test
    public void saysHelloToFranck() {
        String response = new SayHello().sayHelloTo("Franck");

        assertThat(response, is("Hello Franck"));
    }



}