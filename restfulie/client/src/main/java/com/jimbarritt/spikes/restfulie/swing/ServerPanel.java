package com.jimbarritt.spikes.restfulie.swing;

import javax.swing.*;
import java.awt.*;

public class ServerPanel extends JPanel {
    private JTextField serverUrl;
    private JButton restartButton;

    public ServerPanel() {
        super(new BorderLayout());
        serverUrl = new JTextField("http://localhost:8080/restfulie-spike/locations/1");
        super.add(serverUrl, BorderLayout.WEST);
        restartButton = new JButton("Connect");
        super.add(restartButton, BorderLayout.EAST);
    }
}