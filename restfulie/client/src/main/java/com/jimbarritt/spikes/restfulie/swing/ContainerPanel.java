package com.jimbarritt.spikes.restfulie.swing;

import com.jimbarritt.spikes.restfulie.client.*;
import com.jimbarritt.spikes.restfulie.swing.model.*;

import javax.swing.*;
import java.awt.*;

import static java.awt.BorderLayout.CENTER;
import static java.awt.BorderLayout.EAST;
import static java.awt.BorderLayout.NORTH;

public class ContainerPanel extends JPanel {
    private LocationPanel locationPanel;
    private LinkPanel linkPanel;
    private ServerPanel serverPanel;

    public ContainerPanel(ClientGameModel clientGameModel, RemoteGameServer remoteGameServer) {
        super(new BorderLayout());
        layoutComponents(clientGameModel, remoteGameServer);
    }

    private void layoutComponents(ClientGameModel clientGameModel, RemoteGameServer remoteGameServer) {
        serverPanel = new ServerPanel(remoteGameServer, clientGameModel);
        locationPanel = new LocationPanel(clientGameModel);
        linkPanel = new LinkPanel(clientGameModel, remoteGameServer);

        super.add(serverPanel, NORTH);
        super.add(locationPanel, CENTER);
        super.add(linkPanel, EAST);
    }
}