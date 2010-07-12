package com.jimbarritt.spikes.java2d.imagecompare;

import org.apache.log4j.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.htmlunit.*;

@SuppressWarnings("unchecked")
public class WebDriverFactory {
    private static final Logger log = Logger.getLogger(WebDriverFactory.class);

    private static final WebDriverFactory FIREFOX_FACTORY = new WebDriverFactory() {

        @Override public WebDriver createWebDriver() {
            return new FirefoxDriver();
        }
    };

    private static final WebDriverFactory HTMLUNIT_FACTORY = new WebDriverFactory() {
        @Override public WebDriver createWebDriver() {
            return new HtmlUnitDriver();
        }
    };

    private WebDriverFactory factory = HTMLUNIT_FACTORY;

    public WebDriver createWebDriver() {
        WebDriver webDriver = factory.createWebDriver();
        log.debug("Instantiating WebDriver of type: " + webDriver.getClass().getSimpleName());
        return webDriver;
    }

    public WebDriverFactory useFirefox() {
        this.factory = FIREFOX_FACTORY;
        return this;
    }
}
