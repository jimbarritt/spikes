package com.jimbarritt.spikes.metricsexample;

import org.apache.log4j.*;

public class Main {

    private static final Logger log = Logger.getLogger(Main.class);

    public static void main(String[] args) {
        Main main = new Main();
        main.runSimulation();
    }

    private void runSimulation() {
        log.info("Welcome to the BeehiveTest simulator. Its a simple app that is used to test the code metrics of various tools");
    }
}
