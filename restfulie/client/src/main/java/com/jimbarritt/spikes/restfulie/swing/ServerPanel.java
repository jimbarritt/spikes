package com.jimbarritt.spikes.restfulie.swing;

import javax.swing.*;
import java.awt.*;

import static java.awt.BorderLayout.NORTH;
import static java.awt.BorderLayout.WEST;
import static javax.swing.Box.createHorizontalStrut;

public class ServerPanel extends JPanel {
    private JTextField serverUrl;
    private JButton restartButton;

    public ServerPanel() {
        super(new BorderLayout());

        serverUrl = new JTextField("http://localhost:8080/restfulie-spike/locations/1");
        restartButton = new JButton("Connect");

        JPanel container = new JPanel(new BorderLayout());

        JPanel list = new JPanel(new BorderLayout());
        list.add(createHorizontalStrut(650), NORTH);
        list.add(serverUrl, BorderLayout.CENTER);
        list.add(restartButton, BorderLayout.EAST);
        container.add(list, WEST);
        super.add(container, WEST);
    }
}