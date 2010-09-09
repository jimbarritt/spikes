package com.jimbarritt.spikes.restfulie.server.representations;

import br.com.caelum.vraptor.restfulie.*;
import br.com.caelum.vraptor.restfulie.hypermedia.*;
import br.com.caelum.vraptor.restfulie.relation.*;
import com.jimbarritt.spikes.restfulie.server.domain.*;
import com.thoughtworks.xstream.annotations.*;

import java.util.*;

@XStreamAlias("location")
public class LocationRepresentation {

    private final int number;
    private final String description;

    public static LocationRepresentation locationRepresentationOf(Location location) {
        return new LocationRepresentation(location.number(), location.description());
    }
    
    public LocationRepresentation(int number, String description) {
        this.number = number;
        this.description = description;
    }

    


}