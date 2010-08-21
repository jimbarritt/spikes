package com.jimbarritt.spikes.restfulie.io;

import java.io.*;
import java.net.*;

import static java.lang.String.format;

public class SimpleHttpClient {

    public static String getFrom(String urlString) {
        InputStream in = null;
        try {
            URL url = new URL(urlString);
            in = url.openStream();
            return "";
        } catch (Exception e) {
            throw new HttpClientException(format("Problem accessing [%s]", urlString), e);
        } finally {
            tryToClose(in);
        }
    }

    private static void tryToClose(InputStream in) {
        if (in == null) {
            return;
        }

        try {
            in.close();
        } catch (IOException e) {
            throw new HttpClientException("Could not close inputStream", e);
        }
    }


}