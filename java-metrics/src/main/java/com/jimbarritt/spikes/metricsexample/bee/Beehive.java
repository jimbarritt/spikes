package com.jimbarritt.spikes.metricsexample.bee;

import java.util.*;

public class Beehive {

    Collection<Bee> bees = new HashSet<Bee>();

    public void enter(Bee bee) {
        bees.add(bee);
    }

    public boolean contains(Bee bee) {
        return bees.contains(bee);
    }
}