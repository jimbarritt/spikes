package com.jimbarritt.spikes.restfulie.server.resources;

import br.com.caelum.vraptor.*;
import br.com.caelum.vraptor.view.*;
import com.jimbarritt.spikes.restfulie.logging.*;
import static br.com.caelum.vraptor.view.Results.status;

import static java.lang.String.*;

@Resource
public class SimpleHtmlResource {

    private static final StringFormatLogger log = StringFormatLogger.getStringFormatLogger(SimpleHtmlResource.class);

    private final Result result;

    public SimpleHtmlResource(Result result) {
        this.result = result;
    }

    @Get
    @Path("/simplehtml/{id}")
    public void serveSomeHtmlFor(int id) {
        result.use(DefaultHttpResult.class)
                .body(format("<html><h1>Oh yeah baby, VRaptor Rockin! id is %d</h1></html>", id));
    }

    @Post
    @Path("/simplehtml/{id}")
    public void makeAPost(int id, String parameterA, String parameterB) {
        log.info("Received POST @ [%d]", id);
        log.info("parameterA  : %s", parameterA);
        log.info("parameterB : %s", parameterB);

        result.use(status()).created("/newlocation");
    }

    @Put
    @Path("/simplehtml/{id}")
    public void createEncounter(int id, String parameterA, String parameterB) {
        log.info("Received PUT @ [%d]", id);
        log.info("characterUrl : %s", parameterB);
        log.info("creatureUrl  : %s", parameterB);

        result.use(status()).ok();
    }
}