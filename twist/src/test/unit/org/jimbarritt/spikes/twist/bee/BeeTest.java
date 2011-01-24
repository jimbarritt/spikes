package org.jimbarritt.spikes.twist.bee;

import org.junit.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class BeeTest {

    @Test
    public void waggles() {
        Bee bee = new Bee();

        int numWaggles = bee.waggle();

        assertThat(numWaggles, is(0));
    }
}