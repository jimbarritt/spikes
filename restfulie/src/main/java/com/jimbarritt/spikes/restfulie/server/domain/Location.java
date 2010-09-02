package com.jimbarritt.spikes.restfulie.server.domain;

import com.thoughtworks.xstream.annotations.*;

@XStreamAlias("location")
public class Location {

    private final String description;

    public Location(String description) {
        this.description = description;
    }

    public String description() {
        return description;
    }
}