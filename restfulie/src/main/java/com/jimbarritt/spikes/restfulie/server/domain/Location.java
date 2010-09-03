package com.jimbarritt.spikes.restfulie.server.domain;

import br.com.caelum.vraptor.restfulie.*;
import br.com.caelum.vraptor.restfulie.hypermedia.*;
import br.com.caelum.vraptor.restfulie.relation.*;
import com.thoughtworks.xstream.annotations.*;

import java.util.*;

import static java.util.Arrays.asList;


@XStreamAlias("location")
public class Location implements HypermediaResource {

    private final String description;

    public Location(String description) {
        this.description = description;
    }

    public String description() {
        return description;
    }

    @Override public List<Relation> getRelations(Restfulie control) {
        return asList(control.relation("exit").build());
    }
}