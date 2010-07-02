package com.jimbarritt.spikes.stringtemplate.io;

import com.jimbarritt.spikes.stringtemplate.*;

import java.io.*;
import java.nio.*;

public class SafeReader extends Reader {

    private Reader delegate;

    public SafeReader(Reader delegate) {
        this.delegate = delegate;
    }

    public void tryToClose() {
        try {
            delegate.close();
        } catch (IOException e) {
            throw new RuntimeIOException(e);
        }
    }

    @Override public int read(char[] cbuf, int off, int len) throws IOException {
        return delegate.read(cbuf, off, len);
    }

    @Override public void close() throws IOException {
        delegate.close();
    }

    @Override public int read(CharBuffer target) throws IOException {
        return delegate.read(target);
    }

    @Override public int read() throws IOException {
        return delegate.read();
    }

    @Override public int read(char[] cbuf) throws IOException {
        return delegate.read(cbuf);
    }

    @Override public long skip(long n) throws IOException {
        return delegate.skip(n);
    }

    @Override public boolean ready() throws IOException {
        return delegate.ready();
    }

    @Override public boolean markSupported() {
        return delegate.markSupported();
    }

    @Override public void mark(int readAheadLimit) throws IOException {
        delegate.mark(readAheadLimit);
    }

    @Override public void reset() throws IOException {
        delegate.reset();
    }
}