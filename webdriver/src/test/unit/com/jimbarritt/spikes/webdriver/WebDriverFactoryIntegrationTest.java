package com.jimbarritt.spikes.webdriver;

import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.htmlunit.*;


import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class WebDriverFactoryIntegrationTest {

    @Test
    public void createsFirefoxDriver() throws Exception {
        WebDriver webDriver = new WebDriverFactory().useFirefox().createWebDriver();
        assertThat(webDriver.getClass().getSimpleName(), is(FirefoxDriver.class.getSimpleName()));
    }

    @Test
    public void createsHtmlUnitDriver() throws Exception {
        WebDriver webDriver = new WebDriverFactory().createWebDriver();
        assertThat(webDriver.getClass().getSimpleName(), is(HtmlUnitDriver.class.getSimpleName()));
    }

}