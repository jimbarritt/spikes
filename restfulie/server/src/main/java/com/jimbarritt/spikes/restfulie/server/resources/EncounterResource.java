package com.jimbarritt.spikes.restfulie.server.resources;

import br.com.caelum.vraptor.*;
import com.jimbarritt.spikes.restfulie.logging.*;
import com.jimbarritt.spikes.restfulie.server.domain.*;
import com.jimbarritt.spikes.restfulie.server.representations.*;

import static br.com.caelum.vraptor.view.Results.*;
import static com.jimbarritt.spikes.restfulie.logging.StringFormatLogger.*;

@Resource
public class EncounterResource {

    private static final StringFormatLogger log = getStringFormatLogger(LocationResource.class);

    private final Result result;
    private final LocationRepository locationRepository;

    public EncounterResource(Result result, LocationRepository locationRepository) {
        this.result = result;
        this.locationRepository = locationRepository;
    }


    @Get
    @Path("/encounters/{number}")
    public void getEncounter(int number) {
        log.info("Looking for encounter [%d]", number);
        EncounterRequestRepresentation encounterRequestRepresentation = new EncounterRequestRepresentation("fofo", "baba");
        result.use(representation())
                .from(encounterRequestRepresentation)
                .serialize();
    }

}