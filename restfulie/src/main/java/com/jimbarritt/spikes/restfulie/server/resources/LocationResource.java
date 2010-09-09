package com.jimbarritt.spikes.restfulie.server.resources;

import br.com.caelum.vraptor.*;
import com.jimbarritt.spikes.restfulie.server.domain.*;
import org.apache.log4j.*;

import static br.com.caelum.vraptor.view.Results.*;
import static com.jimbarritt.spikes.restfulie.server.representations.LocationRepresentation.locationRepresentationOf;
import static java.lang.String.*;

@Resource
public class LocationResource {
    private static final Logger log = Logger.getLogger(LocationResource.class);

    private final Result result;

    public LocationResource(Result result) {
        this.result = result;
    }


    @Get
    @Path("/locations/{number}")
    public void getLocation(int number) {
        log.info(format("Looking for location [%d]", number));
        Location location = new Location(number, format("This is my location @%d", number));
        result.use(representation())
                .from(locationRepresentationOf(location))
                .serialize();
    }
}