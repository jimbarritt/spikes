package com.jimbarritt.spikes.restfulie.swing;

import javax.swing.*;
import java.awt.*;

import static java.awt.BorderLayout.CENTER;
import static java.awt.BorderLayout.EAST;
import static java.awt.BorderLayout.NORTH;

public class ContainerPanel extends JPanel {
    private LocationPanel locationPanel;
    private LinkPanel linkPanel;
    private ServerPanel serverPanel;

    public ContainerPanel() {
        super(new BorderLayout());
        layoutComponents();
    }

    private void layoutComponents() {
        serverPanel = new ServerPanel();
        locationPanel = new LocationPanel();
        linkPanel = new LinkPanel();

        super.add(serverPanel, NORTH);
        super.add(locationPanel, CENTER);
        super.add(linkPanel, EAST);
    }
}