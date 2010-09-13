package com.jimbarritt.spikes.restfulie.client;

import br.com.caelum.restfulie.*;
import br.com.caelum.restfulie.mediatype.*;
import com.jimbarritt.spikes.restfulie.client.domain.*;
import com.jimbarritt.spikes.restfulie.io.*;
import com.jimbarritt.spikes.restfulie.io.http.*;
import com.jimbarritt.spikes.restfulie.swing.model.*;

import java.io.*;
import java.net.*;
import java.util.*;

import static br.com.caelum.restfulie.Restfulie.resource;
import static java.lang.String.format;

public class RemoteGameServer {
    private RestClient restfulie;
    private final ClientGameModel clientGameModel;

    public RemoteGameServer(ClientGameModel clientGameModel) {
        this.clientGameModel = clientGameModel;
        restfulie = Restfulie.custom();
        restfulie.getMediaTypes().register(new XmlMediaType()
                 .withTypes(Location.class)                 
                 .withTypes(EncounterRequest.class));
    }

    public void connect(URI uri) {
        Response response = restfulie.at(uri)
                .accept("application/xml")
                .get();
        try {
            Location location =  response.getResource();
            clientGameModel.setCurrentLocation(location);
            List<Link> links = resource(location).getLinks("exit");
            List<ExitTo> exitLinks = new ArrayList<ExitTo>();
            String[] locationDescriptions = new String[links.size()];
            int i=0;
            for (Link link : links) {                                
                ExitTo exitTo = new ExitTo(link.getHref(), locationDescriptions[i++]);
                exitLinks.add(exitTo);
            }
            clientGameModel.setExitLinks(exitLinks);
        } catch (IOException e) {
            throw new RuntimeIoException("Could not marshall response", e);
        }
    }


}