package com.jimbarritt.spikes.restfulie.server.resources;

import br.com.caelum.vraptor.*;
import br.com.caelum.vraptor.view.*;

import static java.lang.String.format;

@Resource
public class LocationResource {

    private final Result result;

    public LocationResource(Result result) {
        this.result = result;
    }

    @Get
    @Path("/locations/{id}")
    public void location(int id) {
        result.use(DefaultHttpResult.class).body(format("<html><h1>Oh yeah baby, VRaptor Rockin! id is %d</h1></html>", id));
    }

}