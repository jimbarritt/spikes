package com.jimbarritt.spikes.webdriver;

import magick.*;
import org.apache.log4j.*;
import org.junit.*;

import java.io.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ImageMagickTest {
    private static final Logger log = Logger.getLogger(ImageMagickTest.class);

    @Test
    public void callsImageMagick() throws MagickException {
        log.info("java.library.path=" + System.getProperty("java.library.path"));
        log.info("LibName: " + System.mapLibraryName("JMagick"));
        ImageInfo imageInfo = new ImageInfo("target/webdriver-screenshots/screenshot-0.jpg");
        MagickImage image = new MagickImage(imageInfo);
        MagickImage processedImage = image.addNoiseImage(NoiseType.PoissonNoise);
        processedImage.setFileName("target/webdriver-screenshots/processed-screenshot-0.png");
        image.writeImage(imageInfo);
    }
}