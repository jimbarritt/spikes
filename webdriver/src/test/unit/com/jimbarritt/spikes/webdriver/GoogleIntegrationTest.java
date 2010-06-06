package com.jimbarritt.spikes.webdriver;

import org.apache.log4j.*;
import org.junit.*;
import org.openqa.selenium.*;

import java.net.*;
import java.util.*;

public class GoogleIntegrationTest {


    private static final Logger log = Logger.getLogger(GoogleIntegrationTest.class);

    @Test
    public void searchesGoogle() throws Exception {
        log.info("Current Locale is: COUNTRY - " + Locale.getDefault().getDisplayCountry() + " LANGUAGE - " + Locale.getDefault().getDisplayLanguage());
        URL url = new URL("http://www.google.co.uk");

        WebDriver driver = new WebDriverFactory().createWebDriver();

        driver.navigate().to(url);

        log.info("Result is :\n" + driver.getPageSource());
    }
}
