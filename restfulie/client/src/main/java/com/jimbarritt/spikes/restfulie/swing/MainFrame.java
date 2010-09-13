package com.jimbarritt.spikes.restfulie.swing;

import com.jimbarritt.spikes.restfulie.client.*;
import com.jimbarritt.spikes.restfulie.logging.*;
import com.jimbarritt.spikes.restfulie.swing.model.*;

import javax.swing.*;
import java.awt.*;

import static java.awt.BorderLayout.CENTER;

public class MainFrame extends JFrame {
    private static final StringFormatLogger log = StringFormatLogger.getStringFormatLogger(MainFrame.class);

    public static void main(String[] args) {
        try {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        } catch (Throwable t) {
            log.error("A problem occured", t);
        }
    }

    public MainFrame() throws HeadlessException {
        log.info("Restfulie Spike v1.0");

        super.setSize(800, 600);
        super.setLocation(200, 200);
        super.setTitle("Restfulie Spike");
        super.setLayout(new BorderLayout());
        super.setDefaultCloseOperation(EXIT_ON_CLOSE);

        ClientGameModel clientGameModel = new ClientGameModel();
        RemoteGameServer remoteGameServer = new RemoteGameServer();
        super.add(new ContainerPanel(clientGameModel, remoteGameServer), CENTER);
        

    }
}