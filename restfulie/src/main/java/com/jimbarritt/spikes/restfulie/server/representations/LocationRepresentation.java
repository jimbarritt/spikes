package com.jimbarritt.spikes.restfulie.server.representations;

import com.jimbarritt.spikes.restfulie.server.domain.*;
import com.thoughtworks.xstream.annotations.*;

import java.util.*;

@XStreamAlias("location")
public class LocationRepresentation implements Representation {

    private final Map<RepresentationField, Object> values = new HashMap<RepresentationField, Object>();

    public static enum LocationField implements RepresentationField {
        DESCRIPTION;
    }

    public static LocationRepresentation representationFrom(Location location) {
        LocationRepresentation representation = new LocationRepresentation();
//        location.writeToRepresentation(representation);
        return representation;
    }

    public void appendField(RepresentationField field, String value) {
        values.put(field, value);        
    }
}