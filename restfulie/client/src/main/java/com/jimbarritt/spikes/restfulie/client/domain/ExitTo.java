package com.jimbarritt.spikes.restfulie.client.domain;

public class ExitTo {
    private final String href;
    private final String description;

    public ExitTo(String href, String description) {
        this.href = href;
        this.description = description;
    }

    public String href() {
        return href;
    }

    public String description() {
        return description;
    }
}