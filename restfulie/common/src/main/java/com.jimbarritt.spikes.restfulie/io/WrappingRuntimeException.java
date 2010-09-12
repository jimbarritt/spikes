package com.jimbarritt.spikes.restfulie.io;

import static java.lang.String.format;

public abstract class WrappingRuntimeException extends RuntimeException {

    public WrappingRuntimeException(String message, Throwable cause) {
        super(format("%s (See Cause)", message), cause);
    }
}