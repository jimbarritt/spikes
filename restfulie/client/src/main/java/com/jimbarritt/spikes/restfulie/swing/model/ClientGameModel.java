package com.jimbarritt.spikes.restfulie.swing.model;

import com.jimbarritt.spikes.restfulie.client.domain.*;

import java.beans.*;


public class ClientGameModel  {
    public final static String PROPERTY_CURRENT_LOCATION = "currentLocation";

    private final PropertyChangeSupport propertyChangeSupport;
    private Location currentLocation;

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
}