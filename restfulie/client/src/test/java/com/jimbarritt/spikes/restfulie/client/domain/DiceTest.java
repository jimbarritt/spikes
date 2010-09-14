package com.jimbarritt.spikes.restfulie.client.domain;

import org.junit.*;

import java.util.*;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

public class DiceTest {

    @Test
    public void rollsDice() {
        List<Integer> results = new Dice(2).roll();
        assertThat(results.size(), is(2));
        assertThat(results.get(0), is(notNullValue()));
        assertThat(results.get(1), is(notNullValue()));
    }
}