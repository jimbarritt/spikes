package com.jimbarritt.spikes.restfulie.server.resources;

import br.com.caelum.vraptor.*;
import br.com.caelum.vraptor.view.*;
import com.jimbarritt.spikes.restfulie.server.domain.*;

import static br.com.caelum.vraptor.view.Results.representation;
import static java.lang.String.format;

@Resource
public class LocationResource {

    private final Result result;

    public LocationResource(Result result) {
        this.result = result;
    }


    @Get
    @Path("/locations/{id}")
    public void getLocation(int id) {
        result.use(representation())
              .from(new Location(format("This is my location @", id)))
              .serialize();
    }
}