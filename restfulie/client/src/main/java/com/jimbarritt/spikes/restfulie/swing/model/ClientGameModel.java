package com.jimbarritt.spikes.restfulie.swing.model;

import br.com.caelum.restfulie.*;
import com.jimbarritt.spikes.restfulie.client.domain.*;

import java.beans.*;
import java.util.*;


public class ClientGameModel  {
    public final static String PROPERTY_CURRENT_LOCATION = "currentLocation";
    public final static String PROPERTY_EXIT_LINKS = "exitLinks";

    private final PropertyChangeSupport propertyChangeSupport;
    private Location currentLocation;
    private List<ExitTo> exitLinks;

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
}