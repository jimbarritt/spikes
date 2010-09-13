package com.jimbarritt.spikes.restfulie.swing;

import com.jimbarritt.spikes.restfulie.client.domain.*;

import javax.swing.*;
import java.awt.*;

import static java.awt.BorderLayout.CENTER;
import static java.awt.BorderLayout.NORTH;
import static java.awt.Color.black;
import static java.awt.Color.white;
import static javax.swing.BorderFactory.createEmptyBorder;
import static javax.swing.SwingConstants.LEFT;
import static javax.swing.SwingConstants.TOP;

public class LocationPanel extends JPanel {
    private final AntiAliasedJLabel description;

    public LocationPanel() {
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
    }

    public void setLocation(Location location) {
        description.setText(location.toString());
    }
}