package com.jimbarritt.spikes.restfulie.server.representations;

import com.thoughtworks.xstream.annotations.*;

@XStreamAlias("player")
public class PlayerRepresentation {

    private final String name;

    public PlayerRepresentation(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }
}