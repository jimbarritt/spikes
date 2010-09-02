package com.jimbarritt.spikes.restfulie.server.domain;

public class Location {

    private final String description;

    public Location(String description) {
        this.description = description;
    }

    public String description() {
        return description;
    }
}