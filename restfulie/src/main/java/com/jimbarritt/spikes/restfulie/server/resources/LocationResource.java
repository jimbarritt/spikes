package com.jimbarritt.spikes.restfulie.server.resources;

import br.com.caelum.vraptor.*;
import com.jimbarritt.spikes.restfulie.server.domain.*;
import org.apache.log4j.*;

import static br.com.caelum.vraptor.view.Results.*;
import static java.lang.String.*;
import static java.lang.String.format;

@Resource
public class LocationResource {
    private static final Logger log = Logger.getLogger(LocationResource.class);

    private final Result result;

    public LocationResource(Result result) {
        this.result = result;
    }


    @Get
    @Path("/locations/{id}")
    public void getLocation(int id) {        
        log.info(format("Looking for location [%d]", id));
        result.use(representation())
                .from(new Location(format("This is my location @%d", id)))
                .serialize();
    }
}