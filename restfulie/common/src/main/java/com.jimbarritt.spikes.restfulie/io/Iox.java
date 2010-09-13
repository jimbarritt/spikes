package com.jimbarritt.spikes.restfulie.io;

import java.io.*;
import java.net.*;

import static java.lang.String.format;

public class Iox {

    public static String readAsString(InputStream in, String charsetName) {
        byte[] buffer = new byte[255];
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            int bytesRead = in.read(buffer);
            while (bytesRead != -1) {
                out.write(buffer, 0, bytesRead);
                out.flush();
                bytesRead = in.read(buffer);
            }
            return out.toString(charsetName);
        } catch (IOException e) {
            throw new RuntimeIoException("Could not read from input stream", e);
        } finally {
            tryToClose(out);
        }
    }

    public static void tryToClose(OutputStream out) {
        if (out == null) {
            return;
        }
        try {
            out.close();
        } catch (IOException e) {
            throw new RuntimeIoException("Could not close output stream", e);
        }
    }

    public static String loadClasspathResourceAsString(String resourcePath) {
        URL resourceUrl = Thread.currentThread()
                .getContextClassLoader()
                .getResource(resourcePath);

        if (resourceUrl == null) {
            throw new RuntimeException(format("Could not load resource [%s] from the classpath", resourcePath));
        }

        try {
            InputStream inputStream = resourceUrl.openStream();
            return readAsString(inputStream, "UTF-8");
        } catch (IOException e) {
            throw new RuntimeIoException(format("Problem reading from resource %s", resourcePath), e);
        }

    }
}