package com.jimbarritt.spikes.restfulie.server.domain;

import java.util.*;

public class Location {

    private final String description;
    private final int number;
    private List<Exit> exits;

    public Location(int number, String description) {
        this.number = number;
        this.description = description;
        this.exits = new ArrayList<Exit>();
    }

    public String description() {
        return description;
    }

    public int number() {
        return this.number;
    }

    public List<Exit> getExits() {
        return exits;
    }
}