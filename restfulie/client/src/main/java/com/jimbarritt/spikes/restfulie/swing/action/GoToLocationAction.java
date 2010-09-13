package com.jimbarritt.spikes.restfulie.swing.action;

import com.jimbarritt.spikes.restfulie.client.*;
import com.jimbarritt.spikes.restfulie.client.domain.*;
import com.jimbarritt.spikes.restfulie.swing.*;
import com.jimbarritt.spikes.restfulie.swing.model.*;

import javax.swing.*;
import java.awt.event.*;

public class GoToLocationAction extends AbstractAction {
    private final RemoteGameServer remoteGameServer;
    private final ServerPanel serverPanel;

    public GoToLocationAction(RemoteGameServer remoteGameServer, ServerPanel serverPanel) {
        super("Go");
        this.remoteGameServer = remoteGameServer;
        this.serverPanel = serverPanel;
    }

    @Override public void actionPerformed(ActionEvent e) {
        remoteGameServer.connect(serverPanel.getServerUri());
    }
}