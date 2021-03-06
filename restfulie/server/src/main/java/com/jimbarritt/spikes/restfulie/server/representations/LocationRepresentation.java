package com.jimbarritt.spikes.restfulie.server.representations;

import br.com.caelum.vraptor.restfulie.hypermedia.*;
import br.com.caelum.vraptor.restfulie.relation.*;
import com.jimbarritt.spikes.restfulie.server.domain.*;
import com.jimbarritt.spikes.restfulie.server.resources.*;
import com.thoughtworks.xstream.annotations.*;

import java.util.*;

@XStreamAlias("location")
public class LocationRepresentation implements HypermediaResource {

    private final int number;
    private final String description;

    @XStreamOmitField
    private final List<ExitTo> exitTos;

    public static LocationRepresentation locationRepresentationOf(Location location) {
        return new LocationRepresentation(location.number(), location.describe(), location.getExits());
    }

    private LocationRepresentation(int number, String description, List<ExitTo> exitTos) {
        this.number = number;
        this.description = description;
        this.exitTos = exitTos;
    }

    @Override public void configureRelations(RelationBuilder relationBuilder) {
        for (ExitTo exit: exitTos) {
            relationBuilder.relation("exit").uses(LocationResource.class).getLocation(exit.number());
        }                
    }
}