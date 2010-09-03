package com.jimbarritt.spikes.metricsexample.bee;

import org.junit.*;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class NaughtyBeeTest {

    @Test
    public void testTheDuplicatedMethods() {
        NaughtyBee naughtyBee = new NaughtyBee();

        String part1 = naughtyBee.thisClassHasSomeNaughtDuplication();
        String part2 = naughtyBee.thisClassHasSomeNaughtDuplication2();

        assertThat(part1, is(part2));
    }
}