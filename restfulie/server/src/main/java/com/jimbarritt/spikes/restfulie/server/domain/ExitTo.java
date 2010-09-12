package com.jimbarritt.spikes.restfulie.server.domain;

import static java.lang.String.format;

public class ExitTo {

    private final int number;

    public ExitTo(int number) {
        this.number = number;
    }

    public String describe() {
        return format("(Go to %d)", number);
    }

    public int number() {
        return number;
    }
}