package com.jimbarritt.spikes.restfulie.io;

import org.junit.*;

import java.io.*;

import static com.jimbarritt.spikes.restfulie.io.Iox.readAsUtf8String;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class IoxTest {

    @Test
    public void readsAString() throws Exception {
        String input = "This is my string \u00A3";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes("UTF-8"));

        String result = readAsUtf8String(in);

        assertThat(result, is("This is my string \u00A3"));
    }
}