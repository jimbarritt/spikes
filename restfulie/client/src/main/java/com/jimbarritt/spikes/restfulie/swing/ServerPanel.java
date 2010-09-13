package com.jimbarritt.spikes.restfulie.swing;

import com.jimbarritt.spikes.restfulie.client.*;
import com.jimbarritt.spikes.restfulie.swing.action.*;
import com.jimbarritt.spikes.restfulie.swing.model.*;

import javax.swing.*;
import java.awt.*;
import java.beans.*;
import java.net.*;

import static com.jimbarritt.spikes.restfulie.io.http.HttpClient.*;
import static com.jimbarritt.spikes.restfulie.swing.model.ClientGameModel.*;
import static java.awt.BorderLayout.*;
import static javax.swing.Box.*;

public class ServerPanel extends JPanel implements PropertyChangeListener {
    private JTextField serverUrl;
    private JButton connectButton;
    private JButton homeButton;


    public ServerPanel(RemoteGameServer remoteGameServer, ClientGameModel clientGameModel) {
        super(new BorderLayout());

        serverUrl = new JTextField("http://localhost:8080/restfulie-spike/locations/1");
        connectButton = new JButton(new GoToLocationAction(remoteGameServer, this));
        homeButton = new JButton(new HomeAction(remoteGameServer));

        JPanel container = new JPanel(new BorderLayout());

        JPanel list = new JPanel(new BorderLayout());
        list.add(createHorizontalStrut(650), NORTH);
        list.add(serverUrl, BorderLayout.CENTER);

        JPanel buttonContainer = new JPanel();
        buttonContainer.add(connectButton);
        buttonContainer.add(homeButton);
        list.add(buttonContainer, EAST);
        container.add(list, WEST);
        super.add(container, WEST);

        clientGameModel.addPropertyChangeListener(this);
    }

    public URI getServerUri() {
        return toUri(serverUrl.getText());
    }

    @Override public void propertyChange(PropertyChangeEvent evt) {
        if (PROPERTY_CURRENT_URI.equals(evt.getPropertyName())) {
            serverUrl.setText(toExternalForm((URI) evt.getNewValue()));
        }
    }
}