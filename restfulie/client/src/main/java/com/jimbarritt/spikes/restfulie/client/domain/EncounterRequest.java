package com.jimbarritt.spikes.restfulie.client.domain;

import com.thoughtworks.xstream.annotations.*;

@XStreamAlias("encounterRequest")
public class EncounterRequest {

    private String characterUrl;
    private String creatureUrl;

    public EncounterRequest withCharacterUrl(String characterUrl) {
        this.characterUrl = characterUrl;
        return this;
    }


    public EncounterRequest withCreatureUrl(String creatureUrl) {
        this.creatureUrl = creatureUrl;
        return this;
    }
}