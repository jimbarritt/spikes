package com.jimbarritt.spikes;

import org.apache.log4j.*;
import org.junit.*;

import java.io.*;

public class IdeConsoleLinkToSourceTest {
    private static final Logger log = Logger.getLogger(IdeConsoleLinkToSourceTest.class);

    @Test
    public void canClickOnAFileInTheConsoleAndGoToTheLineOfCode() {
        log.info(String.format("Check it at %s. (%s.java:%d)", getClass().getName(), getClass().getSimpleName(), 15));
    }

    @Test
    public void someOtherFormatsThatWork() {
        log.info("at " + getClass().getName() + ".canClickOnAFileInTheConsoleAndGoToTheLineOfCode(IdeConsoleLinkToSourceTest.java:15)");
    }

    @Test
    public void oneThatDoesntWork() {
        log.info("at " + getClass().getSimpleName() + ".m(IdeConsoleLinkToSourceTest.java:15)");
        log.info("at com." + getClass().getSimpleName() + ".canClickOnAFileInTheConsoleAndGoToTheLineOfCode(IdeConsoleLinkToSourceTest.java:15)");
    }
}