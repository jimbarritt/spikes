package com.jimbarritt.spikes.restfulie.swing;

import com.jimbarritt.spikes.restfulie.client.domain.*;
import com.jimbarritt.spikes.restfulie.swing.model.*;

import javax.swing.*;
import java.awt.*;
import java.beans.*;
import java.util.List;

import static com.jimbarritt.spikes.restfulie.swing.model.ClientGameModel.*;
import static java.awt.BorderLayout.*;
import static javax.swing.BoxLayout.Y_AXIS;

public class LinkPanel extends JPanel implements PropertyChangeListener {
    private JPanel buttonPanel;
    private final ClientGameModel clientGameModel;

    public LinkPanel(ClientGameModel clientGameModel) {
        super(new BorderLayout(), true);
        this.clientGameModel = clientGameModel;
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

    @Override public void propertyChange(PropertyChangeEvent evt) {
        if (PROPERTY_EXIT_LINKS.equals(evt.getPropertyName())) {
            buildLinkButtons((List<ExitTo>)evt.getNewValue());
        }
    }

    private void buildLinkButtons(List<ExitTo> exitLinks) {
        buttonPanel.removeAll();
        JPanel buttonContainer = new JPanel(new GridLayout(exitLinks.size(), 1));
        for (ExitTo exit : exitLinks) {
            JButton button = new JButton(exit.description());
            buttonContainer.add(button);
        }
        buttonPanel.add(buttonContainer, BorderLayout.NORTH);
    }
}