package com.jimbarritt.spikes.restfulie.client;

import com.thoughtworks.xstream.annotations.*;

@XStreamAlias("location")
public class Location {

    private final String description;


    public Location(String description) {
        this.description = description;
    }

    public String toString() {
        return description;
    }
}