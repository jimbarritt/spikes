package com.jimbarritt.spikes.restfulie.client;

import br.com.caelum.restfulie.*;
import br.com.caelum.restfulie.mediatype.*;
import com.jimbarritt.spikes.restfulie.client.domain.*;
import com.jimbarritt.spikes.restfulie.io.*;
import com.jimbarritt.spikes.restfulie.io.http.*;
import com.jimbarritt.spikes.restfulie.logging.*;
import com.jimbarritt.spikes.restfulie.swing.model.*;

import java.io.*;
import java.net.*;
import java.util.*;

import static br.com.caelum.restfulie.Restfulie.resource;
import static java.lang.String.format;

public class RemoteGameServer {
    private static final StringFormatLogger log = StringFormatLogger.getStringFormatLogger(RemoteGameServer.class);

    private final RestClient restfulie;
    private final ClientGameModel clientGameModel;
    private final LocationDescriptionParser locationDescriptionParser;

    public RemoteGameServer(ClientGameModel clientGameModel) {
        this.clientGameModel = clientGameModel;
        this.locationDescriptionParser = new LocationDescriptionParser();
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
            log.info("Response from server was \n%s", location.toString());
            clientGameModel.setCurrentLocation(location);
            clientGameModel.setCurrentURI(uri);
            List<Link> links = resource(location).getLinks("exit");
            List<ExitTo> exitLinks = new ArrayList<ExitTo>();
            String[] linkDescriptions = locationDescriptionParser.parseDescription(location.toString());
            int i=0;
            for (Link link : links) {                                
                ExitTo exitTo = new ExitTo(link.getHref(), linkDescriptions[i]);
                exitLinks.add(exitTo);
                ++i;
            }
            clientGameModel.setExitLinks(exitLinks);
        } catch (IOException e) {
            throw new RuntimeIoException("Could not marshall response", e);
        }
    }


}