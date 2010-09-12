package com.jimbarritt.spikes.restfulie.io.http;

public class HttpResponse {
    private final int responseCode;

    public HttpResponse(int responseCode) {

        this.responseCode = responseCode;
    }

    public int responseCode() {
        return responseCode;
    }
}