package com.jimbarritt.spikes.metricsexample.bee;

import com.jimbarritt.spikes.metricsexample.flower.*;
import org.junit.*;

import static com.jimbarritt.spikes.metricsexample.flower.Flower.GERANIUM;
import static com.jimbarritt.spikes.metricsexample.flower.Flower.LILY;
import static com.jimbarritt.spikes.metricsexample.flower.Flower.ROSE;

public class ComplexBeeTest {

    @Test
    public void testBuzzing() {
        ComplexBee complexBee = new ComplexBee(true);
        complexBee.buzz();
    }

    @Test
    public void doesntBuzz() {
        ComplexBee complexBee = new ComplexBee(false);
        complexBee.buzz();
    }

    @Test
    public void wagglesInA() {
        ComplexBee complexBee = new ComplexBee(true, new Flower(GERANIUM, true));
        complexBee.waggle();
    }

    @Test
    public void wagglesInAMinor() {
        ComplexBee complexBee = new ComplexBee(true, new Flower(GERANIUM, false));
        complexBee.waggle();
    }

    @Test
    public void wagglesInB() {
        ComplexBee complexBee = new ComplexBee(true, new Flower(ROSE, true));
        complexBee.waggle();
    }

    @Test
    public void wiggles() {
        ComplexBee complexBee = new ComplexBee(true, new Flower(LILY, true));
        complexBee.waggle();        
    }

}