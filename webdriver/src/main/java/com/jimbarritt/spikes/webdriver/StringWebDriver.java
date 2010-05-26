package com.jimbarritt.spikes.webdriver;

import org.apache.log4j.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.htmlunit.*;
import org.apache.commons.io.IOUtils;

import java.io.*;

public class StringWebDriver {

    private static final Logger LOG = Logger.getLogger(StringWebDriver.class.getName());

    private boolean useFirefox;

    public StringWebDriver() {
        useFirefox = true;                
    }

      public WebDriver initialiseWith(String html) throws IOException {
          WebDriver driver = createWebDriver();
          File tempFile = File.createTempFile(getClass().getName() + "tempHtmlFile", ".html");

          LOG.debug("Writing temp file: " + tempFile.getAbsolutePath());

          FileOutputStream out = null;
          try {
              out = new FileOutputStream(tempFile);
              out.write(html.getBytes("UTF-8"));
              driver.get(tempFile.toURI().toURL().toExternalForm());
              if (!tempFile.delete()) {
                  throw new RuntimeException("Could not delete file [" + tempFile.getAbsolutePath() + "]");
              }
          } finally {
              IOUtils.closeQuietly(out);
          }
          return driver;
      }

      private WebDriver createWebDriver() {
          return useFirefox ? new FirefoxDriver() : new HtmlUnitDriver();
      }

      public static WebElement loadElementByIdFromRepresentation(String representation, String elementId) throws IOException {
          WebDriver stringWebDriver = new StringWebDriver().initialiseWith(representation);
          return stringWebDriver.findElement(By.id(elementId));
      }
    

}
