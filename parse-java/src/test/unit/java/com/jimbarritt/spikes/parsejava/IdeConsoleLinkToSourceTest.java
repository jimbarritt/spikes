package com.jimbarritt.spikes.parsejava;

import org.apache.log4j.*;
import org.junit.*;

import java.io.*;

public class IdeConsoleLinkToSourceTest {
    private static final Logger log = Logger.getLogger(IdeConsoleLinkToSourceTest.class);

    @Test
    public void canClickOnAFileInTheConsoleAndGoToTheLineOfCode() {
        File sourceFile = new File("", getClass().getName().replaceAll("\\.", File.separator) + ".java");
        log.info("I found something interesting on the line of code at " + getClass().getName() + ".(" + getClass().getSimpleName() + ".java:15)");
        log.info("at " + getClass().getName() + ".canClickOnAFileInTheConsoleAndGoToTheLineOfCode(IdeConsoleLinkToSourceTest.java:15)");

        log.info("at " + getClass().getSimpleName() + ".m(IdeConsoleLinkToSourceTest.java:15)");
    }
}
