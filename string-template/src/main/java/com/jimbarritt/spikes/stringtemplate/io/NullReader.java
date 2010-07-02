package com.jimbarritt.spikes.stringtemplate.io;

import java.io.*;

public class NullReader extends SafeReader {
    public static SafeReader nullSafeReader() {
        return new NullReader();
    }

    public NullReader() {
        super(null);
    }

    @Override public void tryToClose() {
    }

    @Override public int read(char[] cbuf, int off, int len) throws IOException {
        return 0;
    }

    @Override public void close() throws IOException {
    }
}