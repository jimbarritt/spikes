package com.jimbarritt.spikes.restfulie.io;

public class HttpClientException extends WrappingRuntimeException {
    public HttpClientException(String message, Throwable cause) {
        super(message, cause);
    }
}