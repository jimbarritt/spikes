package com.jimbarritt.spikes.restfulie.swing.model;

import br.com.caelum.restfulie.*;
import com.jimbarritt.spikes.restfulie.client.domain.*;

import javax.swing.*;
import java.beans.*;
import java.net.*;
import java.util.*;


public class ClientGameModel  {
    public final static String PROPERTY_CURRENT_LOCATION = "currentLocation";
    public final static String PROPERTY_EXIT_LINKS = "exitLinks";
    public final static String PROPERTY_CURRENT_URI = "currentUri";
   
    private final PropertyChangeSupport propertyChangeSupport;
    private Location currentLocation;
    private List<ExitTo> exitLinks;
    private URI currentUri;

    public ClientGameModel() {
        propertyChangeSupport = new PropertyChangeSupport(this);
    }

    public void setCurrentLocation(Location currentLocation) {
        Location oldValue = this.currentLocation;
        this.currentLocation = currentLocation;
        propertyChangeSupport.firePropertyChange(PROPERTY_CURRENT_LOCATION, oldValue, this.currentLocation);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    public Location currentLocation() {
        return currentLocation;
    }

    public void setExitLinks(List<ExitTo> exitLinks) {
        List<ExitTo> oldLinks = this.exitLinks;
        this.exitLinks = exitLinks;
        propertyChangeSupport.firePropertyChange(PROPERTY_EXIT_LINKS, oldLinks, this.exitLinks);
    }

    public void setCurrentURI(URI currentUri) {
        URI oldUri = this.currentUri;
        this.currentUri = currentUri;
        propertyChangeSupport.firePropertyChange(PROPERTY_CURRENT_URI, oldUri, this.currentUri);
    }
}