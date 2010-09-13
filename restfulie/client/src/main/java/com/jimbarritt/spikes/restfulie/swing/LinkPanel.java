package com.jimbarritt.spikes.restfulie.swing;

import com.jimbarritt.spikes.restfulie.client.*;
import com.jimbarritt.spikes.restfulie.client.domain.*;
import com.jimbarritt.spikes.restfulie.swing.action.*;
import com.jimbarritt.spikes.restfulie.swing.model.*;

import javax.swing.*;
import java.awt.*;
import java.beans.*;
import java.util.List;

import static com.jimbarritt.spikes.restfulie.swing.model.ClientGameModel.*;
import static java.awt.BorderLayout.*;

public class LinkPanel extends JPanel implements PropertyChangeListener {
    private JPanel buttonPanel;
    private final ClientGameModel clientGameModel;
    private final RemoteGameServer remoteGameServer;
    private JPanel buttonContainer = new JPanel();

    public LinkPanel(ClientGameModel clientGameModel, RemoteGameServer remoteGameServer) {
        super(new BorderLayout(), true);
        this.clientGameModel = clientGameModel;
        this.remoteGameServer = remoteGameServer;
        super.setMinimumSize(new Dimension(200, 400));
        super.setSize(new Dimension(200, 400));

        buttonPanel = initButtons();
        super.add(buttonPanel, BorderLayout.EAST);
        clientGameModel.addPropertyChangeListener(this);
    }

    private JPanel initButtons() {
        JPanel buttonPanel = new JPanel(new BorderLayout());
        buttonPanel.add(Box.createHorizontalStrut(150), SOUTH);
        return buttonPanel;
    }

    private void buildLinkButtons(List<ExitTo> exitLinks) {
        buttonContainer.removeAll();
        buttonPanel.removeAll();
        buttonContainer = new JPanel(new GridLayout(exitLinks.size(), 1));
        for (ExitTo exit : exitLinks) {
            JButton button = new JButton(new ExitLinkAction(exit.description(), remoteGameServer, exit.href()));
            buttonContainer.add(button);
        }
        buttonPanel.add(buttonContainer, BorderLayout.NORTH);
        SwingUtilities.invokeLater(new Runnable() {

            @Override public void run() {
                getRootPane().invalidate();
                getRootPane().repaint();
            }
        });

    }

    @Override public void propertyChange(PropertyChangeEvent evt) {
        if (PROPERTY_EXIT_LINKS.equals(evt.getPropertyName())) {
            buildLinkButtons((List<ExitTo>) evt.getNewValue());
        }
    }
}