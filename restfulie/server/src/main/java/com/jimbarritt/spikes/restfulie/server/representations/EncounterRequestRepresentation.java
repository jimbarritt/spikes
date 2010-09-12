package com.jimbarritt.spikes.restfulie.server.representations;

import com.thoughtworks.xstream.annotations.*;

@XStreamAlias("encounterRequest")
public class EncounterRequestRepresentation {
    private final String characterUrl;
    private final String creatureUrl;

    public EncounterRequestRepresentation(String characterUrl, String creatureUrl) {
        this.characterUrl = characterUrl;
        this.creatureUrl = creatureUrl;
    }

    public String toString() {
        return new StringBuilder()
                        .append("characterUrl : ").append(characterUrl).append("\n")
                        .append("creatureUrl  : ").append(creatureUrl).append("\n")
                        .toString();
    }

}