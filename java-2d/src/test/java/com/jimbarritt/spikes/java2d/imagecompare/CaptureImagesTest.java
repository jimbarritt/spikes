package com.jimbarritt.spikes.java2d.imagecompare;

import org.apache.log4j.*;
import org.junit.*;
import org.openqa.selenium.firefox.*;

import java.io.*;
import java.net.*;

import static java.lang.Thread.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class CaptureImagesTest {
    private static final Logger log = Logger.getLogger(CaptureImagesTest.class);
    private FirefoxDriver firefoxDriver;

    @Before
    public void onceBeforeEachTest() {
        firefoxDriver = (FirefoxDriver) new WebDriverFactory().useFirefox().createWebDriver();
    }

    @After
    public void onceAfterEachTest() {
        firefoxDriver.close();
    }

    @Test
    public void createsImagesFromHtml() throws Exception {

        URL masterUrl = currentThread().getContextClassLoader().getResource("html/testPage_master.xhtml");
        firefoxDriver.get(masterUrl.toExternalForm());

        log.info("SOURCE:\n[[" + firefoxDriver.getPageSource() + "]]");

        File screenshotFile = new WebdriverScreenGrab(firefoxDriver).takeScreenshot();

        assertThat(screenshotFile.exists(), is(true));

        log.info("Wrote file to [" + screenshotFile.getAbsolutePath() + "]");

    }
}