package com.jimbarritt.spikes.restfulie.client;

import br.com.caelum.restfulie.*;
import br.com.caelum.restfulie.mediatype.*;
import com.jimbarritt.spikes.restfulie.client.domain.*;
import com.jimbarritt.spikes.restfulie.io.*;

import java.io.*;
import java.net.*;

public class RemoteGameServer {
    private RestClient restfulie;

    public RemoteGameServer() {
        restfulie = Restfulie.custom();
        restfulie.getMediaTypes().register(new XmlMediaType()
                 .withTypes(Location.class)
                 .withTypes(EncounterRequest.class));
    }

    public Location connect(URI uri) {
        Response response = restfulie.at(uri)
                .accept("application/xml")
                .get();
        try {
            return response.getResource();
        } catch (IOException e) {
            throw new RuntimeIoException("Could not marshall response", e);
        }
    }


}