package com.jimbarritt.spikes.restfulie.swing.action;

import com.jimbarritt.spikes.restfulie.client.*;
import com.jimbarritt.spikes.restfulie.swing.*;

import javax.swing.*;
import java.awt.event.*;

public class GoToLocationAction extends AbstractAction {
    private final RemoteGameServer remoteGameServer;
    private final ServerPanel serverPanel;
    private final LinkPanel linkPanel;

    public GoToLocationAction(RemoteGameServer remoteGameServer, ServerPanel serverPanel, LinkPanel linkPanel) {
        super("Go");
        this.remoteGameServer = remoteGameServer;
        this.serverPanel = serverPanel;
        this.linkPanel = linkPanel;
    }

    @Override public void actionPerformed(ActionEvent e) {
        remoteGameServer.connect(serverPanel.getServerUri());       
    }
}