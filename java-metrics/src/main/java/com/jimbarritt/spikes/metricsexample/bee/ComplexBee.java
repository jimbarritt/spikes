package com.jimbarritt.spikes.metricsexample.bee;

import com.jimbarritt.spikes.metricsexample.flower.*;
import org.apache.log4j.*;

import java.util.*;

import static com.jimbarritt.spikes.metricsexample.flower.Flower.GERANIUM;
import static com.jimbarritt.spikes.metricsexample.flower.Flower.ROSE;
import static java.util.Arrays.asList;

/**
 * This Bee has some nasty complex behaviour for cyclomatic complexity 
 */
public class ComplexBee {
    private static final Logger log = Logger.getLogger(ComplexBee.class);

    private final boolean shouldBuzz;
    private final List<Flower> visitedFlowers;

    public ComplexBee(boolean shouldBuzz, Flower... visitedFlowers)  {
        this.shouldBuzz = shouldBuzz;
        this.visitedFlowers = asList(visitedFlowers);
    }

    public void buzz() {
        if (shouldBuzz) {
            log.info("bzzzzz....");
        } else {
            log.info("shhhh...");
        }
    }

    public void waggle() {
        for (Flower flower : visitedFlowers) {
            switch(flower.type()) {
                case GERANIUM:
                    if (flower.hasPollen()) {
                        log.info("waggle-A");
                    } else {
                        log.info("wiggle-A-minor");                        
                    }
                    break;
                case ROSE:
                    log.info("waggle-B");
                    break;
                default:
                    log.info("wiggle");
            }
        }
    }
}