package com.jimbarritt.spikes.restfulie.client.domain;

import com.thoughtworks.xstream.annotations.*;

import java.util.regex.*;

import static java.lang.String.format;

@XStreamAlias("location")
public class Location {

    private final int number;
    private final String description;



    public Location(int number, String description) {
        this.number = number;
        this.description = description;
    }

    public String toString() {
        return format("%s", description);
    }

    public int number() {
        return number;
    }


    
}