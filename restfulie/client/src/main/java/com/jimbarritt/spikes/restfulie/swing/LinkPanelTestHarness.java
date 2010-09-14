package com.jimbarritt.spikes.restfulie.swing;

import com.jimbarritt.spikes.restfulie.client.*;
import com.jimbarritt.spikes.restfulie.client.domain.*;
import com.jimbarritt.spikes.restfulie.swing.model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

import static java.awt.BorderLayout.*;
import static java.util.Arrays.asList;

public class LinkPanelTestHarness {

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        JPanel panel = new JPanel(new BorderLayout());

        frame.add(panel);

        ClientGameModel model = new ClientGameModel();
        LinkPanel linkPanel = new LinkPanel(model, new RemoteGameServer(model));
        panel.add(linkPanel, CENTER);

        List<ExitTo> exitLinks = new ArrayList(asList(new ExitTo("href", "link 1"), new ExitTo("bfre", "link 3")));
        linkPanel.setExitLinks(exitLinks);

        TestControlPanel testControlPanel = new TestControlPanel(linkPanel, exitLinks);

        panel.add(testControlPanel, BorderLayout.NORTH);

        frame.setSize(300, 400);
        frame.setVisible(true);
    }

    public static class TestControlPanel extends JPanel {
        private final LinkPanel linkPanel;
        private final List<ExitTo> exits;
        private int currentButtonId = 100;

        public TestControlPanel(LinkPanel linkPanel, List<ExitTo> exits) {
            this.linkPanel = linkPanel;
            this.exits = exits;
            JButton addButton = new JButton(new AddButtonAction(this));
            JButton removeButton = new JButton(new RemoveButtonAction(this));
            super.add(addButton);
            super.add(removeButton);
        }


    }

    private static class AddButtonAction extends AbstractAction {
        private final TestControlPanel testControlPanel;
        private int currentButtonId = 100;

        public AddButtonAction(TestControlPanel testControlPanel) {
            super("add");
            this.testControlPanel = testControlPanel;
        }

        @Override public void actionPerformed(ActionEvent e) {
            String link = "foobar" + currentButtonId++;
            testControlPanel.exits.add(new ExitTo(link, link));
            testControlPanel.linkPanel.setExitLinks(testControlPanel.exits);
        }

    }

    private static class RemoveButtonAction extends AbstractAction {
        private final TestControlPanel testControlPanel;
        private int currentButtonId = 100;

        public RemoveButtonAction(TestControlPanel testControlPanel) {
            super("remove");
            this.testControlPanel = testControlPanel;
        }

        @Override public void actionPerformed(ActionEvent e) {
            testControlPanel.exits.remove(testControlPanel.exits.size() - 1);
            testControlPanel.linkPanel.setExitLinks(testControlPanel.exits);
        }

    }
}