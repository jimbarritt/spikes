package com.jimbarritt.spikes.restfulie.server.resources;

import br.com.caelum.vraptor.*;
import com.jimbarritt.spikes.restfulie.server.representations.*;

import static br.com.caelum.vraptor.view.Results.representation;
import static java.lang.String.format;

@Resource
public class LinkDescriptionResource {

    private final Result result;

    public LinkDescriptionResource(Result result) {
        this.result = result;
    }

    @Get
    @Path("/relations/{name}")
    public void getRelationDescription(String name) {
        if (!"exit".equals(name)) {
            throw new RuntimeException(format("No relation called [%s]", name));
        }
        LinkDescriptionRepresentation linkDescriptionRepresentation = new LinkDescriptionRepresentation("fofo");
        result.use(representation())
                .from(linkDescriptionRepresentation)
                .serialize();
    }

}