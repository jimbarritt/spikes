package com.jimbarritt.spikes.restfulie.io;

public class RuntimeIoException extends WrappingRuntimeException {

    public RuntimeIoException(String message, Throwable cause) {
        super(message, cause);
    }

}