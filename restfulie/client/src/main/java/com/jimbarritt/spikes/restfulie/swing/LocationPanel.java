package com.jimbarritt.spikes.restfulie.swing;

import com.jimbarritt.spikes.restfulie.client.domain.*;

import javax.swing.*;
import java.awt.*;

import static java.awt.BorderLayout.CENTER;
import static java.awt.Color.black;
import static java.awt.Color.white;

public class LocationPanel extends JPanel {
    private final JLabel description;

    public LocationPanel() {
        super(new BorderLayout(), true);
        description = new JLabel("You have not connected to the game server");
        description.setVerticalAlignment((int)JPanel.TOP_ALIGNMENT);
        description.setBackground(black);
        description.setForeground(white);
        description.setOpaque(true);
        description.setFont(Font.getFont("Courier"));
        super.add(description, CENTER);
    }

    public void setLocation(Location location) {
        description.setText(location.toString());           
    }
}