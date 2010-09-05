package com.jimbarritt.spikes.metricsexample.flower;

import org.junit.*;

public class FlowerTest {

    @Test
    public void testFlower() {
        Flower flower = new Flower();
        flower.hasPollen();
        flower.type();
    }
}