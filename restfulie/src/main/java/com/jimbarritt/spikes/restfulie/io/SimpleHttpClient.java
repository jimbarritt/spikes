package com.jimbarritt.spikes.restfulie.io;

import java.io.*;
import java.net.*;
import java.nio.charset.*;

import static com.jimbarritt.spikes.restfulie.io.Iox.readAsString;
import static java.lang.String.format;
import static java.nio.charset.Charset.defaultCharset;

public class SimpleHttpClient {

    private final Charset defaultCharset = defaultCharset();

    public String getFrom(String urlString) {
        InputStream in = null;
        try {
            URL url = new URL(urlString);
            URLConnection urlConnection = url.openConnection();
            in = url.openStream();
            return readAsString(in, extractContentCharset(urlConnection.getContentType()));
        } catch (Exception e) {
            throw new HttpClientException(format("Problem accessing [%s]", urlString), e);
        } finally {
            tryToClose(in);
        }
    }

    private String extractContentCharset(String contentTypeHeaderValue) {
        if (contentTypeHeaderValue == null || (!contentTypeHeaderValue.contains(";"))) {
            return defaultCharset.name();
        }

        String[] contentTypCharsetSplit = contentTypeHeaderValue.split(";");
        String[] contentCharsetSplit = contentTypCharsetSplit[1].split("=");
        return contentCharsetSplit[1];
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