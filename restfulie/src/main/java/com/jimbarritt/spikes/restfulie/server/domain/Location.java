package com.jimbarritt.spikes.restfulie.server.domain;

import java.util.*;

public class Location {

    private final String description;
    private final int number;
    private List<ExitTo> exitTos;

    public Location(int number, String description) {
        this.number = number;
        this.description = description;
        this.exitTos = new ArrayList<ExitTo>();
    }

    public Location withExitTo(Location otherLocation) {
        exitTos.add(new ExitTo(otherLocation.number()));
        return this;
    }

    public String description() {
        return description;
    }

    public int number() {
        return this.number;
    }

    public List<ExitTo> getExits() {
        return exitTos;
    }
}