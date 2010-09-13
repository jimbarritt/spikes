package com.jimbarritt.spikes.restfulie.swing;

import com.jimbarritt.spikes.restfulie.client.domain.*;
import com.jimbarritt.spikes.restfulie.swing.model.*;

import javax.swing.*;
import java.awt.*;
import java.beans.*;

import static java.awt.BorderLayout.CENTER;
import static java.awt.Color.*;
import static java.lang.String.format;
import static javax.swing.BorderFactory.*;
import static javax.swing.SwingConstants.*;

public class LocationPanel extends JPanel implements PropertyChangeListener {
    private final AntiAliasedJLabel description;

    public LocationPanel(ClientGameModel clientGameModel) {
        super(new BorderLayout(), true);
        super.setBorder(createEmptyBorder(5, 5, 5, 5));
        JPanel container = new JPanel(new BorderLayout());
        container.setBorder(createEmptyBorder(10, 10, 10, 10));
        container.setBackground(black);
        super.add(container, CENTER);
        description = new AntiAliasedJLabel("You have not connected to the game server.");
        description.setVerticalAlignment(TOP);
        description.setHorizontalAlignment(JLabel.LEFT);

        description.setBackground(black);
        description.setForeground(white);
        description.setOpaque(true);

        description.setFont(new Font("Courier", Font.PLAIN, 16));
        container.add(description, CENTER);

        setLocation(clientGameModel.currentLocation());
        clientGameModel.addPropertyChangeListener(this);
    }

    public void setLocation(Location location) {
        String locationDescription = "No current location";
        if (location != null) {
            locationDescription = format("<html><p>%s</p></html>", location.toString());
        }
        description.setText(locationDescription);
    }

    @Override public void propertyChange(PropertyChangeEvent evt) {
        setLocation((Location) evt.getNewValue());
    }
}