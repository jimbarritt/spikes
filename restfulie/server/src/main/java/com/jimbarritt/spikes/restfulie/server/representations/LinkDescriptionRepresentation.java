package com.jimbarritt.spikes.restfulie.server.representations;

import com.thoughtworks.xstream.annotations.*;

@XStreamAlias("linkDescription")
public class LinkDescriptionRepresentation {

    private final String description;

    public LinkDescriptionRepresentation(String description) {
        this.description = description;
    }

    public String toString() {
        return this.description;
    }
}