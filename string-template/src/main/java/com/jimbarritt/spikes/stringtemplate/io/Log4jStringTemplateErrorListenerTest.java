package com.jimbarritt.spikes.stringtemplate.io;

import org.junit.*;

import static org.hamcrest.core.Is.*;
import static org.junit.Assert.*;

public class Log4jStringTemplateErrorListenerTest {

    @Test
    public void recordsErrors() {
        Log4jStringTemplateErrorListener listener = new Log4jStringTemplateErrorListener();

        listener.error("foobar", null);

        assertThat(listener.errors().get(0), is("foobar"));
    }

    @Test
    public void recordsWarnings() {
        Log4jStringTemplateErrorListener listener = new Log4jStringTemplateErrorListener();

        listener.warning("warningfoo");

        assertThat(listener.warnings().get(0), is("warningfoo"));
    }
}