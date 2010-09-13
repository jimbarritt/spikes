package com.jimbarritt.spikes.restfulie.swing.action;

import com.jimbarritt.spikes.restfulie.client.*;
import com.jimbarritt.spikes.restfulie.client.domain.*;
import com.jimbarritt.spikes.restfulie.swing.*;
import com.jimbarritt.spikes.restfulie.swing.model.*;

import javax.swing.*;
import java.awt.event.*;

public class ConnectToServerAction extends AbstractAction {
    private final RemoteGameServer remoteGameServer;
    private final ClientGameModel clientGameModel;
    private final ServerPanel serverPanel;

    public ConnectToServerAction(RemoteGameServer remoteGameServer, ClientGameModel clientGameModel, ServerPanel serverPanel) {
        super("Connect");
        this.remoteGameServer = remoteGameServer;
        this.clientGameModel = clientGameModel;
        this.serverPanel = serverPanel;
    }

    @Override public void actionPerformed(ActionEvent e) {
        Location location = remoteGameServer.connect(serverPanel.getServerUri());

        clientGameModel.setCurrentLocation(location);
    }
}