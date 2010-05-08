package com.jimbarritt.spikes.parsejava;

import org.apache.log4j.*;
import org.junit.*;

import java.io.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ScansTheInsectsIntegrationTest {
    private static final Logger log = Logger.getLogger(ScansTheInsectsIntegrationTest.class);
    
    @Test
    public void scansTheBeePackages() {
        FileScanner scanner = new FileScanner(new File("./src/test/unit/java/com/jimbarritt/spikes/parsejava/example"), ".java");
        FileCounter fileCounter = new FileCounter();
        scanner.scan(fileCounter);

        assertThat(fileCounter.fileCount(), is(7L));
    }

    private static final class FileCounter implements FileScanningAction {

        private long fileCount;

        @Override
        public void scanFile(File file) {
            fileCount++;
            log.debug("Scanning file [" + file.getAbsolutePath() + "]");
        }

        public long fileCount() {
            return fileCount;
        }
    }
}
