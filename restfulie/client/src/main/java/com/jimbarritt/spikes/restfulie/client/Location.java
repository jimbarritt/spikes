package com.jimbarritt.spikes.restfulie.client;

import com.thoughtworks.xstream.annotations.*;

@XStreamAlias("location")
public class Location {

    private final int number;
    private final String description;


    public Location(int number, String description) {
        this.number = number;
        this.description = description;
    }

    public String toString() {
        return String.format("Location %d - %s", number, description);
    }
}