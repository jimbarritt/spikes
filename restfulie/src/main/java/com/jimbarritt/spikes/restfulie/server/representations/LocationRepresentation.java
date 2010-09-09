package com.jimbarritt.spikes.restfulie.server.representations;

import br.com.caelum.vraptor.restfulie.*;
import br.com.caelum.vraptor.restfulie.hypermedia.*;
import br.com.caelum.vraptor.restfulie.relation.*;
import com.jimbarritt.spikes.restfulie.server.domain.*;
import com.thoughtworks.xstream.annotations.*;

import java.util.*;

@XStreamAlias("location")
public class LocationRepresentation implements HypermediaResource {

    private final int number;
    private final String description;

    @XStreamOmitField
    private final List<Exit> exits;

    public static LocationRepresentation locationRepresentationOf(Location location) {
        return new LocationRepresentation(location.number(), location.description(), location.getExits());
    }
    
    private LocationRepresentation(int number, String description, List<Exit> exits) {
        this.number = number;
        this.description = description;
        this.exits = exits;
    }


    @Override public List<Relation> getRelations(Restfulie control) {

        for (Exit exit : exits) {
            control.relation("exit").uses(LocationRepresentation.class);
        }
        return control.getRelations();
    }
}