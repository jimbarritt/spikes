package com.jimbarritt.spikes.restfulie.swing;

import com.jimbarritt.spikes.restfulie.client.*;
import com.jimbarritt.spikes.restfulie.io.*;
import com.jimbarritt.spikes.restfulie.swing.action.*;
import com.jimbarritt.spikes.restfulie.swing.model.*;

import javax.swing.*;
import java.awt.*;
import java.net.*;

import static java.awt.BorderLayout.*;
import static java.lang.String.*;
import static javax.swing.Box.*;

public class ServerPanel extends JPanel {
    private JTextField serverUrl;
    private JButton connectButton;


    public ServerPanel(RemoteGameServer remoteGameServer, ClientGameModel clientGameModel) {
        super(new BorderLayout());

        serverUrl = new JTextField("http://localhost:8080/restfulie-spike/locations/1");
        connectButton = new JButton(new ConnectToServerAction(remoteGameServer, clientGameModel, this));

        JPanel container = new JPanel(new BorderLayout());

        JPanel list = new JPanel(new BorderLayout());
        list.add(createHorizontalStrut(650), NORTH);
        list.add(serverUrl, BorderLayout.CENTER);
        list.add(connectButton, BorderLayout.EAST);
        container.add(list, WEST);
        super.add(container, WEST);
    }

    public URI getServerUri() {
        return toUri(serverUrl.getText());
    }

    private static URI toUri(String url) {
        try {
            return new URL(url).toURI();
        } catch (Exception t) {
            throw new RuntimeIoException(format("Could not convert url [%s]", url), t);
        }
    }

}