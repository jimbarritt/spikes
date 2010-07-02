package com.jimbarritt.spikes.stringtemplate.io;

import java.io.*;

public class SafeReader extends Reader {

    private Reader delegate;

    public SafeReader(Reader delegate) {
        this.delegate = delegate;
    }

    public void tryToClose() {
        try {
            close();
        } catch (IOException e) {
            throw new RuntimeIOException(e);
        }
    }

    @Override public int read(char[] cbuf, int off, int len) throws IOException {
        return delegate.read(cbuf, off, len);
    }


    @Override public int read() throws IOException {
        return delegate.read();
    }



    @Override public boolean ready() throws IOException {
        return delegate.ready();
    }

    @Override public void close() throws IOException {
        delegate.close();
    }

}