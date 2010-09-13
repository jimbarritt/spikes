package com.jimbarritt.spikes.restfulie.client.domain;

import org.junit.*;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class LocationDescriptionParserTest {

    @Test
    public void extractsLinks() {
        String input = "Some text, (Go to 33), some more text (Go to 55).";
        String[] descriptions = new LocationDescriptionParser().parseDescription(input);

        assertThat(descriptions.length, is(2));
        assertThat(descriptions[0], is("Go to 33"));
        assertThat(descriptions[1], is("Go to 55"));
    }

}