package com.jimbarritt.spikes.restfulie.swing.action;

import com.jimbarritt.spikes.restfulie.client.*;
import com.jimbarritt.spikes.restfulie.io.http.*;
import com.jimbarritt.spikes.restfulie.swing.*;

import javax.swing.*;
import java.awt.event.*;

import static com.jimbarritt.spikes.restfulie.io.http.HttpClient.toUri;

public class HomeAction extends AbstractAction {
    private final RemoteGameServer remoteGameServer;
    private final LinkPanel linkPanel;
    private String href;

    public HomeAction(RemoteGameServer remoteGameServer, LinkPanel linkPanel) {
        super("Home");
        this.remoteGameServer = remoteGameServer;
        this.linkPanel = linkPanel;
        this.href = "http://localhost:8080/restfulie-spike/locations/1001";
    }

    @Override public void actionPerformed(ActionEvent e) {
        remoteGameServer.connect(toUri(href));
    }

    public String href() {
        return this.href;
    }
}