package com.jimbarritt.spikes.restfulie.server.resources;

import br.com.caelum.vraptor.*;
import com.jimbarritt.spikes.restfulie.logging.*;
import com.jimbarritt.spikes.restfulie.server.representations.*;

import static br.com.caelum.vraptor.view.Results.representation;
import static br.com.caelum.vraptor.view.Results.status;

@Resource
public class PlayerResource {

    private static final StringFormatLogger log = StringFormatLogger.getStringFormatLogger(PlayerResource.class);

    private final Result result;

    public PlayerResource(Result result) {
        this.result = result;
    }

    @Get
    @Path("/players/{id}")
    public void getPlayer(String id) {
        log.info("Looking for player [%s]", id);
        result.use(representation())
                .from(new PlayerRepresentation("Johnny Foo"))
                .serialize();
    }

    @Put
    @Path("/players/{id}")
    @Consumes("application/xml")
    public void putPlayer(String id, PlayerRepresentation playerRepresentation) {
        log.info("Updating player [%s] to be ", id, playerRepresentation);
        result.use(status()).ok();
    }
}