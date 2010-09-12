package com.jimbarritt.spikes.restfulie.server.resources;

import br.com.caelum.vraptor.*;
import br.com.caelum.vraptor.restfulie.*;
import br.com.caelum.vraptor.restfulie.hypermedia.*;
import br.com.caelum.vraptor.restfulie.relation.*;
import com.jimbarritt.spikes.restfulie.logging.*;
import com.jimbarritt.spikes.restfulie.server.domain.*;
import com.jimbarritt.spikes.restfulie.server.representations.*;

import java.util.*;

import static br.com.caelum.vraptor.view.Results.*;
import static com.jimbarritt.spikes.restfulie.logging.StringFormatLogger.*;
import static com.jimbarritt.spikes.restfulie.server.representations.LocationRepresentation.*;

@Resource
public class LocationResource {
    private static final StringFormatLogger log = getStringFormatLogger(LocationResource.class);

    private final Result result;
    private final LocationRepository locationRepository;

    public LocationResource(Result result, LocationRepository locationRepository) {
        this.result = result;
        this.locationRepository = locationRepository;
    }


    @Get
    @Path("/locations/{number}")
    public void getLocation(int number) {
        log.info("Looking for location [%d]", number);
        Location location = locationRepository.get(number);
        result.use(representation())
                .from(locationRepresentationOf(location))
                .serialize();
    }

    @Post
    @Path("/locations/{number}")
    @Consumes("application/xml")
    public void createEncounter(int number, EncounterRequestRepresentation encounterRequest) {
        log.info("Received post @ location [%d]", number);
        log.info("encounterRequest: \n%s", encounterRequest);


        result.use(status()).created("/encounters/666");
    }


}