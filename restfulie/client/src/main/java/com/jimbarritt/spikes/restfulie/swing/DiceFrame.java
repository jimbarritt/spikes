package com.jimbarritt.spikes.restfulie.swing;

import com.jimbarritt.spikes.restfulie.client.domain.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

import static java.awt.Color.*;
import static java.lang.String.*;
import static javax.swing.BoxLayout.*;
import static javax.swing.SwingConstants.*;

public class DiceFrame extends JFrame {
    private final Dice dice = new Dice(2);
    private AntiAliasedJLabel die1;
    private AntiAliasedJLabel die2;

    public DiceFrame() throws HeadlessException {
        super.setSize(150, 100);
        super.setLocation(1200, 60);
        super.setTitle("Roll 'em");


        super.getContentPane().setLayout(new BorderLayout());
        JButton rollButton = new JButton(new RollAction(this));
        super.getContentPane().add(rollButton, BorderLayout.SOUTH);

        JPanel diceResult = new JPanel();
        die1 = new AntiAliasedJLabel("");                
        die1.setFont(new Font("Courier", Font.PLAIN, 32));

        die2 = new AntiAliasedJLabel("");
        die2.setFont(new Font("Courier", Font.PLAIN, 32));
        diceResult.setLayout(new BorderLayout());
        diceResult.add(die1, BorderLayout.WEST);
        diceResult.add(die2, BorderLayout.EAST);

        super.getContentPane().add(diceResult, BorderLayout.CENTER);
    }

    private void rollEm() {
        List<Integer> results = dice.roll();
        die1.setText(format(" %s", results.get(0)));
        die2.setText(format("%s ", results.get(1)));
        SwingUtilities.invokeLater(new Runnable() {

            @Override public void run() {
                invalidate();
                repaint();
            }
        });
    }

    private static class RollAction extends AbstractAction {
        private final DiceFrame diceFrame;

        public RollAction(DiceFrame diceFrame) {
            super("Roll 'em");
            this.diceFrame = diceFrame;
        }

        @Override public void actionPerformed(ActionEvent e) {
            diceFrame.rollEm();
        }
    }


}