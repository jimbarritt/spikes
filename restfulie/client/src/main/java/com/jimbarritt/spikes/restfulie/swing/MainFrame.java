package com.jimbarritt.spikes.restfulie.swing;

import com.jimbarritt.spikes.restfulie.client.*;
import com.jimbarritt.spikes.restfulie.logging.*;
import com.jimbarritt.spikes.restfulie.swing.model.*;
import org.apache.log4j.*;

import javax.swing.*;
import java.awt.*;

import static java.awt.BorderLayout.CENTER;

public class MainFrame extends JFrame {
    private static final Logger log = Logger.getLogger(MainFrame.class);

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

        super.setSize(900, 800);
        super.setLocation(200, 60);
        super.setTitle("Restfulie Spike");
        super.setLayout(new BorderLayout());
        super.setDefaultCloseOperation(EXIT_ON_CLOSE);

        ClientGameModel clientGameModel = new ClientGameModel();
        RemoteGameServer remoteGameServer = new RemoteGameServer(clientGameModel);
        super.add(new ContainerPanel(clientGameModel, remoteGameServer), CENTER);
        

    }
}