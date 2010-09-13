package com.jimbarritt.spikes.restfulie.swing;

import com.jimbarritt.spikes.restfulie.client.domain.*;

import javax.swing.*;
import java.awt.*;
import java.beans.*;
import java.util.List;

import static com.jimbarritt.spikes.restfulie.swing.model.ClientGameModel.*;
import static java.awt.BorderLayout.*;

public class LinkPanel extends JPanel implements PropertyChangeListener {
    private JPanel buttonPanel;

    public LinkPanel() {
        super(new BorderLayout(), true);
        super.setMinimumSize(new Dimension(200, 400));
        super.setSize(new Dimension(200, 400));

        buttonPanel = initButtons();
        super.add(buttonPanel, BorderLayout.EAST);
    }

    private JPanel initButtons() {
        JPanel buttonPanel = new JPanel(new BorderLayout());
        buttonPanel.add(Box.createHorizontalStrut(150), NORTH);
        return buttonPanel;
    }

    @Override public void propertyChange(PropertyChangeEvent evt) {
        if (PROPERTY_EXIT_LINKS.equals(evt.getPropertyName())) {
            buildLinkButtons((List<ExitTo>)evt.getNewValue());
        }
    }

    private void buildLinkButtons(List<ExitTo> exitLinks) {
        buttonPanel.removeAll();
        for (ExitTo exit : exitLinks) {
            JButton button = new JButton(exit.description());
            buttonPanel.add(button);
        }
    }
}