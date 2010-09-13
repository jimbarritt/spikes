package com.jimbarritt.spikes.restfulie.swing.action;

import com.jimbarritt.spikes.restfulie.client.*;
import com.jimbarritt.spikes.restfulie.client.domain.*;
import com.jimbarritt.spikes.restfulie.swing.*;
import com.jimbarritt.spikes.restfulie.swing.model.*;

import javax.swing.*;
import java.awt.event.*;

public class ConnectToServerAction extends AbstractAction {
    private final RemoteGameServer remoteGameServer;
    private final ServerPanel serverPanel;

    public ConnectToServerAction(RemoteGameServer remoteGameServer, ServerPanel serverPanel) {
        super("Connect");
        this.remoteGameServer = remoteGameServer;
        this.serverPanel = serverPanel;
    }

    @Override public void actionPerformed(ActionEvent e) {
        remoteGameServer.connect(serverPanel.getServerUri());
    }
}