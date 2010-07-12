package com.jimbarritt.spikes.webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.*;
import sun.rmi.rmic.iiop.*;

import java.io.*;

import static java.lang.String.format;

public class WebdriverScreenGrab {

	String SCREEN_SHOT_ID = "current_screen_shot_id";

    private static int SCREENSHOT_ID = 0;
    private final FirefoxDriver firefoxDriver;
    private final File outputDir;

	public WebdriverScreenGrab(FirefoxDriver firefoxDriver) {
        this.firefoxDriver = firefoxDriver;
        this.outputDir = new File("./target/webdriver-screenshots");
    }
   
	public File takeScreenshot() {
        ensureOutputDirExists(outputDir);
        File outputFile = new File(outputDir, format("screenshot-%d.jpg", nextScreenshotId()));
        File tempFile = firefoxDriver.getScreenshotAs(OutputType.FILE);
        if (!tempFile.renameTo(outputFile)) {
            throw new RuntimeException("Could not rename temp file [" + tempFile.getAbsolutePath() + "] to [" + outputFile.getAbsolutePath() + "]");
        }
        return outputFile;
    }

    private static void ensureOutputDirExists(File outputDir) {
        if (outputDir.exists()) {
            return;
        }
        if (!outputDir.mkdirs()) {
            throw new RuntimeException("Could not create output directory [" + outputDir.getAbsolutePath() + "]");
        }
    }

    private static int nextScreenshotId() {
        return SCREENSHOT_ID++;
    }

}