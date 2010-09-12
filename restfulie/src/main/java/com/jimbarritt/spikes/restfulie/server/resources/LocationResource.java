package com.jimbarritt.spikes.restfulie.server.resources;

import br.com.caelum.vraptor.*;
import br.com.caelum.vraptor.restfulie.*;
import br.com.caelum.vraptor.restfulie.hypermedia.*;
import br.com.caelum.vraptor.restfulie.relation.*;
import com.jimbarritt.spikes.restfulie.logging.*;
import com.jimbarritt.spikes.restfulie.server.domain.*;

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
    @Path("/locations/{number}/createEncounter")
    public void createEncounter(int number, String characterUrl, String creatureUrl) {
        log.info("Received post @ location [%d]", number);
        log.info("characterUrl : %s", characterUrl);
        log.info("creatureUrl  : %s", creatureUrl);


    }


}