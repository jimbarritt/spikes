package com.jimbarritt.spikes.restfulie.server.domain;

public class Location {

    private final String description;
    private final int number;

    public Location(int number, String description) {
        this.number = number;
        this.description = description;
    }

    public String description() {
        return description;
    }

    public int number() {
        return this.number;
    }
}