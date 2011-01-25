package org.jimbarritt.spikes.twist.bee;

import org.apache.log4j.*;

import java.util.*;

public class BeeWaggler {
    private static final Logger log = Logger.getLogger(BeeWaggler.class);

    public List<Bee> waggleSomeBees() {
        log.error("Hello Im a gooona waggle me some bees dude!");
        return Arrays.asList(new Bee(), new Bee(), new Bee());
    }
}