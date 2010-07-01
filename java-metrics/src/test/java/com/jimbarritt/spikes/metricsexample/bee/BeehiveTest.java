package com.jimbarritt.spikes.metricsexample.bee;

import org.junit.*;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class BeehiveTest {

    @Test
    public void beesCanEnterTheHive() {
        Beehive beehive = new Beehive();

        Bee bee = new Bee();

        beehive.enter(bee);

        assertThat(beehive.contains(bee), is(true));
    }



}