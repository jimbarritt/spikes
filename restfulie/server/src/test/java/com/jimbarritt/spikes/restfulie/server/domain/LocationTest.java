package com.jimbarritt.spikes.restfulie.server.domain;

import org.junit.*;

import java.text.*;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class LocationTest {

    @Test
    public void demoMessageFormat() {
        String output = MessageFormat.format("String {0}, String {1}", "a", "b");
        assertThat(output, is("String a, String b"));
    }

    @Test
    public void populatesDescriptionWithExits() {
        Location locationA = new Location(99, "a");
        Location locationB = new Location(88, "b");

        Location locationC = new Location(3)
                                    .appendDescription("You are in a room.")
                                    .appendDescription(" To leave by the west door {0}, or by the east {1}.")
                                    .withExitTo(locationA)
                                    .withExitTo(locationB);

        assertThat(locationC.describe(), is("You are in a room. To leave by the west door (Go to 99), or by the east (Go to 88)."));
    }
}