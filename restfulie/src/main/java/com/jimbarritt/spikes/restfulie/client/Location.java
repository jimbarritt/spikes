package com.jimbarritt.spikes.restfulie.client;

public class Location {

    private final String description;


    public Location(String description) {
        this.description = description;
    }

    public String toString() {
        return description;
    }
}