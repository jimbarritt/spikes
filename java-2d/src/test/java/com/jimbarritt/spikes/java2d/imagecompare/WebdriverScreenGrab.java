package com.jimbarritt.spikes.java2d.imagecompare;

import org.openqa.selenium.firefox.*;

import java.io.*;

import static java.lang.String.format;

public class WebdriverScreenGrab {

	private static int SCREENSHOT_ID = 0;
    private final FirefoxDriver firefoxDriver;
    private final File outputDir;

	public WebdriverScreenGrab(FirefoxDriver firefoxDriver) {
        this.firefoxDriver = firefoxDriver;
        this.outputDir = new File("./target/webdriver-screenshots");
    }

    @SuppressWarnings("deprecated")
	public File takeScreenshot() {
        ensureOutputDirExists(outputDir);
        File outputFile = new File(outputDir, format("screenshot-%d.jpg", nextScreenshotId()));
		firefoxDriver.saveScreenshot(outputFile);
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