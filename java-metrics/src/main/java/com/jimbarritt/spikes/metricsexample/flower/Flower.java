package com.jimbarritt.spikes.metricsexample.flower;

public class Flower {
    public static final int GERANIUM = 1;
    public static final int ROSE = 2;
    public static final int LILY = 3;

    private final int type;
    private final boolean hasPollen;

    public Flower() {
        this(GERANIUM, true);
    }

    public Flower(int type, boolean hasPollen) {
        this.type = type;
        this.hasPollen = hasPollen;
    }

    public int type() {
        return this.type;
    }

    public boolean hasPollen() {
        return hasPollen;
    }
}