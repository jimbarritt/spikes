package com.jimbarritt.spikes.restfulie.swing;

import javax.swing.*;
import java.awt.*;

import static java.awt.BorderLayout.NORTH;

public class LinkPanel extends JPanel {

    public LinkPanel() {
        super(new BorderLayout(), true);
        super.setMinimumSize(new Dimension(200, 400));
        super.setSize(new Dimension(200, 400));

        JPanel buttonPanel = initButtons();
        super.add(buttonPanel, BorderLayout.EAST);
    }

    private JPanel initButtons() {
        JPanel buttonPanel = new JPanel(new BorderLayout());
        buttonPanel.add(Box.createHorizontalStrut(150), NORTH);
        return buttonPanel;
    }

}