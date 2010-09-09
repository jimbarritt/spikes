package com.jimbarritt.spikes.restfulie.server.resources;

import br.com.caelum.vraptor.*;
import com.jimbarritt.spikes.restfulie.logging.*;
import com.jimbarritt.spikes.restfulie.server.domain.*;

import static br.com.caelum.vraptor.view.Results.*;
import static com.jimbarritt.spikes.restfulie.logging.StringFormatLogger.*;
import static com.jimbarritt.spikes.restfulie.server.representations.LocationRepresentation.*;
import static java.lang.String.*;

@Resource
public class LocationResource {
    private static final StringFormatLogger log = getStringFormatLogger(LocationResource.class);

    private final Result result;

    public LocationResource(Result result) {
        this.result = result;
    }


    @Get
    @Path("/locations/{number}")
    public void getLocation(int number) {
        log.info("Looking for location [%d]", number);
        Location location = new Location(number, format("This is my location @%d", number));
        result.use(representation())
              .from(locationRepresentationOf(location))
              .serialize();
    }
}