package com.jimbarritt.spikes.restfulie.io;

import org.junit.*;

import java.io.*;

import static com.jimbarritt.spikes.restfulie.io.Iox.readAsString;
import static com.jimbarritt.spikes.restfulie.io.Iox.tryToClose;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class IoxTest {

    @Test
    public void readsAString() throws Exception {
        String input = "This is my string \u00A3";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes("UTF-8"));

        String result = readAsString(in, "UTF-8");

        assertThat(result, is("This is my string \u00A3"));
    }

    @Test(expected = RuntimeIoException.class)
    public void wrapsCloseFailureInIoException() {
        OutputStream failingStream = new FailingOutputStream();
        
        tryToClose(failingStream);
    }

    private static class FailingOutputStream extends OutputStream {

        @Override public void write(int b) throws IOException {

        }

        @Override public void close() throws IOException {
            throw new IOException("BANG!");
        }
    }
}