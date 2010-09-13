package com.jimbarritt.spikes.restfulie.swing;

import javax.swing.*;
import java.awt.*;

public class AntiAliasedJLabel extends JLabel {

    public AntiAliasedJLabel(String text) {
        super(formatText(text));
    }

    private static String formatText(String text) {
        return text.replaceAll("\\n", "<br/>");
    }

    @Override public void setText(String text) {
        super.setText(formatText(text));
    }

    @Override public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        super.paint(g);
    }
}