package com.jimbarritt.spikes.restfulie.client;

import com.thoughtworks.xstream.annotations.*;

@XStreamAlias("player")
public class Player {
    private final String name;

    public Player(String name) {
        this.name = name;
    }

    public String toString() {
        return this.name;
    }
}