package com.jimbarritt.spikes.stringtemplate;

import static java.lang.String.format;

public class SayHello {
    private final SayHelloService sayHelloService;

    public SayHello(SayHelloService sayHelloService) {
        this.sayHelloService = sayHelloService;
    }

    public String sayHelloTo(String name) {
        return sayHelloService.formatHelloMessage(name);
    }
}