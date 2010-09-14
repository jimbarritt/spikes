package com.jimbarritt.spikes.restfulie.swing;

import com.jimbarritt.spikes.restfulie.client.*;
import com.jimbarritt.spikes.restfulie.client.domain.*;
import com.jimbarritt.spikes.restfulie.logging.*;
import com.jimbarritt.spikes.restfulie.swing.action.*;
import com.jimbarritt.spikes.restfulie.swing.model.*;

import javax.swing.*;
import java.awt.*;
import java.beans.*;
import java.util.*;
import java.util.List;

import static com.jimbarritt.spikes.restfulie.swing.model.ClientGameModel.*;
import static java.awt.BorderLayout.*;
import static javax.swing.Box.createHorizontalStrut;
import static javax.swing.BoxLayout.Y_AXIS;

public class LinkPanel extends JPanel implements PropertyChangeListener {
    private static final StringFormatLogger log = StringFormatLogger.getStringFormatLogger(LinkPanel.class);
    private JPanel buttonPanel;
    private final ClientGameModel clientGameModel;
    private final RemoteGameServer remoteGameServer;
    private JPanel panelOfButtons;
    private List<JButton> currentButtons = new ArrayList<JButton>();

    public LinkPanel(ClientGameModel clientGameModel, RemoteGameServer remoteGameServer) {
        super(new BorderLayout(), true);
        this.clientGameModel = clientGameModel;
        this.remoteGameServer = remoteGameServer;
        buttonPanel = new JPanel(new BorderLayout());
        panelOfButtons = new JPanel();
        panelOfButtons.setLayout(new BoxLayout(panelOfButtons, Y_AXIS));
        buttonPanel.add(createHorizontalStrut(100), NORTH);
        buttonPanel.add(panelOfButtons, CENTER);
        super.add(buttonPanel, EAST);

        initButtons(new ArrayList<ExitTo>());

        clientGameModel.addPropertyChangeListener(this);
    }

    private void initButtons(List<ExitTo> exitLinks) {
        for (JButton btn : currentButtons) {
            panelOfButtons.remove(btn);
        }
        currentButtons.clear();

        for (ExitTo exit : exitLinks) {
            JButton button = new JButton(new ExitLinkAction(exit.description(), remoteGameServer, exit.href()));
            log.debug("Adding new button %s", button.getAction().toString());
            panelOfButtons.add(button);
            currentButtons.add(button);
        }

        refreshView();
    }

    private void refreshView() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override public void run() {
                panelOfButtons.doLayout();
                panelOfButtons.invalidate();
                panelOfButtons.repaint();
            }
        });
    }


    public void setExitLinks(List<ExitTo> exitLinks) {
        initButtons(exitLinks);
    }

    @Override public void propertyChange(final PropertyChangeEvent evt) {
        if (PROPERTY_EXIT_LINKS.equals(evt.getPropertyName())) {
            setExitLinks((List<ExitTo>) evt.getNewValue());
        }
    }
}