package com.jimbarritt.spikes.restfulie.swing.action;

import com.jimbarritt.spikes.restfulie.client.*;
import com.jimbarritt.spikes.restfulie.io.*;
import com.jimbarritt.spikes.restfulie.io.http.*;
import com.jimbarritt.spikes.restfulie.swing.model.*;

import javax.swing.*;
import java.awt.event.*;

public class ExitLinkAction extends AbstractAction {
    private final String url;
    private final RemoteGameServer remoteGameServer;

    public ExitLinkAction(String name, RemoteGameServer remoteGameServer, String url) {
        super(name);
        this.remoteGameServer = remoteGameServer;
        this.url = url;
    }

    @Override public void actionPerformed(ActionEvent e) {
        remoteGameServer.connect(HttpClient.toUri(url));        
    }
}