package com.jimbarritt.spikes.stringtemplate.io;

import org.junit.*;

import java.io.*;

public class NullReaderTest {

    @Test
    public void implementsNoOpsForAllMethods() throws Exception {
        SafeReader reader = new NullReader();

        reader.read(new char[]{}, -4, -5);
        reader.close();
        reader.tryToClose();
    }
}