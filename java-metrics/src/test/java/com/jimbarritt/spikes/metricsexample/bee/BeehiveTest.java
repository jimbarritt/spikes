package com.jimbarritt.spikes.metricsexample.bee;

import org.junit.*;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class BeehiveTest {

    @Test
    public void beesCanEnterTheHive() {
        Bee bee = new Bee();
        Beehive beehive = new Beehive();

        beehive.enter(bee);

        assertThat(beehive.contains(bee), is(true));
    }



}