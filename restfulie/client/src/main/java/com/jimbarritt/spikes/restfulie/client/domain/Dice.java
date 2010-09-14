package com.jimbarritt.spikes.restfulie.client.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Dice {

    private static int parseDiceCount(String[] args, int index) {
        try {
            return Integer.parseInt(args[0]);
        }
        catch (Exception e) {
            System.err.println("Argument " + index + " should be integer number of dice");
            System.exit(1);
        }
        return 1;
    }

    private Random random = new Random();
    private int diceCount;

    public Dice(int diceCount) {
        this.diceCount = diceCount;
    }

    public List<Integer> roll() {
        List<Integer> diceRoll = new ArrayList<Integer>();
        for (int i = 0; i < diceCount; i++) {
            diceRoll.add(rollDie());
        }
        return diceRoll;
    }

    private int rollDie() {
        return random.nextInt(6) + 1;
    }
}
