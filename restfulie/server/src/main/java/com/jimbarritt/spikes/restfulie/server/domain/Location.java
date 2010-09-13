package com.jimbarritt.spikes.restfulie.server.domain;

import java.util.*;

import static com.jimbarritt.spikes.restfulie.server.domain.LocationDescription.simpleDescription;

public class Location {

    private final LocationDescription description;
    private final int number;
    private List<ExitTo> exitTos;

    public Location(int number) {
        this(number, new LocationDescription());
    }
    public Location(int number, String description) {
        this(number, simpleDescription(description));        
    }
    public Location(int number, LocationDescription description) {
        this.number = number;
        this.description = description;
        this.exitTos = new ArrayList<ExitTo>();
    }

    public Location withExitTo(int otherLocationNumber) {
        exitTos.add(new ExitTo(otherLocationNumber));
        return this;
    }

    public String describe() {
        return description.toString(exitTos);
    }

    public int number() {
        return this.number;
    }

    public List<ExitTo> getExits() {
        return exitTos;
    }

    public Location appendDescription(String text) {
        description.appendText(text);
        return this; 
    }
}