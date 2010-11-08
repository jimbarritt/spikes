package org.jimbarritt.spikes.jvmencoding;

import org.apache.log4j.*;

public class Main {

    private static final Logger log = Logger.getLogger(Main.class);

    public static void main(String[] args) {
        log.info("Welcome to the JVM encoding spike.");

        JvmEncodingDetector jvmEncodingDetector = new JvmEncodingDetector();

        log.info(jvmEncodingDetector.describe());

    }
}