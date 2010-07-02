package com.jimbarritt.spikes.stringtemplate.io;

import java.io.*;

public class RuntimeIOException extends RuntimeException {
    public RuntimeIOException(IOException e) {
        super("An IOException was caught, see cause for details.", e);
    }
}