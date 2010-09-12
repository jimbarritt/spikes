package com.jimbarritt.spikes.restfulie.functionaltest;

import static java.lang.String.format;

public class RemoteApplication {        

    public String rootUri() {
        return uriForPath("/");
    }

    public String uriForPath(String path) {
        return format(path.startsWith("/") ? "%s%s" : "%s/%s", getBaseUri(), path);
    }

    private String getBaseUri() {
        return "http://localhost:8080/restfulie-spike";
    }
}