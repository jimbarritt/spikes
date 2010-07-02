package com.jimbarritt.spikes.stringtemplate.io;

import org.apache.log4j.*;
import org.junit.*;

import java.io.*;
import java.net.*;
import java.util.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SafeReaderTest {
    private static final Logger log = Logger.getLogger(SafeReaderTest.class);

    @Test(expected = RuntimeIOException.class)
    public void doesntThrowAnExceptionWhenYouTryToCloseIt()  {
        SafeReader safeReader = new SafeReader(new FailingReader());        
        safeReader.tryToClose();
    }

    @Test
    public void readsFromDelegate()  throws IOException {
        URL testResourceUrl = this.getClass().getResource("testResource.properties");
        SafeReader safeReader = new SafeReader(new InputStreamReader(testResourceUrl.openStream()));

        List<String> lines = new ArrayList<String>();
        try {
            BufferedReader in = new BufferedReader(safeReader);
            while (in.ready()) {
                lines.add(in.readLine());
            }
        } finally {
            safeReader.tryToClose();
        }
        log.info("Lines: " + lines);
        assertThat(lines.size(), is(1));
    }

    private static class FailingReader extends Reader {

        @Override public int read(char[] cbuf, int off, int len) throws IOException {
            throw new IOException("Bang! - READ");
        }

        @Override public void close() throws IOException {
            throw new IOException("Bang! - CLOSE");
        }
    }
}