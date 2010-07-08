package com.jimbarritt.spikes.java2d.imagecompare;

import org.apache.log4j.*;
import org.junit.*;
import org.openqa.selenium.*;

import java.net.*;

import static java.lang.Thread.currentThread;

public class CaptureImagesTest {
    private static final Logger log = Logger.getLogger(CaptureImagesTest.class);

    @Test
    public void createsImagesFromHtml() throws Exception {
        WebDriver webDriver = new WebDriverFactory().useFirefox().createWebDriver();

        URL masterUrl = currentThread().getContextClassLoader().getResource("html/testPage_master.xhtml");
        webDriver.get(masterUrl.toExternalForm());

        log.info("SOURCE:\n[[" + webDriver.getPageSource() + "]]");
    }
}