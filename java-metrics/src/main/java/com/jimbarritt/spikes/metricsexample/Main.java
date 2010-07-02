package com.jimbarritt.spikes.metricsexample;

import org.apache.log4j.*;

/**
 * This is the main entry point for our test application.
 * @author Jim
 *
 */
public class Main {

    private static final Logger log = Logger.getLogger(Main.class); // An Inline comment

    public static void main(String[] args) {
        Main main = new Main();
        main.runSimulation();
    }

    private void runSimulation() {
        // @todo do some work in here
        log.info("Welcome to the BeehiveTest simulator. Its a simple app that is used to test the code metrics of various tools");
    }
}