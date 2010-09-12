package com.jimbarritt.spikes.restfulie.io.http;

import com.jimbarritt.spikes.restfulie.io.*;
import com.jimbarritt.spikes.restfulie.logging.*;

import java.io.*;
import java.net.*;
import java.nio.charset.*;
import java.util.*;

import static com.jimbarritt.spikes.restfulie.io.Iox.*;
import static java.lang.String.*;
import static java.nio.charset.Charset.*;

public class HttpConsumer {
    StringFormatLogger log = StringFormatLogger.getStringFormatLogger(HttpConsumer.class);

    private final Charset defaultCharset = defaultCharset();
    private List<RequestParameter> requestParameters = new ArrayList<RequestParameter>();

    public String getFrom(String urlString) {
        InputStream in = null;
        HttpURLConnection httpConnection = null;
        try {
            URL url = new URL(urlString);
            httpConnection = (HttpURLConnection) url.openConnection();
            in = url.openStream();
            return readAsString(in, extractContentCharset(httpConnection.getContentType()));
        } catch (Exception e) {
            throw new HttpClientException(format("Problem accessing [%s]", urlString), e);
        } finally {
            tryToDisconnect(httpConnection);
            tryToClose(in);
        }
    }

    public HttpResponse postTo(String urlString) {
        return executeOutputMethod(urlString, "POST");
    }


    public HttpResponse putTo(String urlString) {
        return executeOutputMethod(urlString, "PUT");
    }

    private HttpResponse executeOutputMethod(String urlString, String method) {
        InputStream in = null;
        try {
            log.info("%s : %s", method, urlString);
            URL url = new URL(urlString);
            HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
            httpConnection.setDoOutput(true);
            httpConnection.setRequestMethod(method);
            httpConnection.connect();

            OutputStreamWriter out = new OutputStreamWriter(httpConnection.getOutputStream());
            for (RequestParameter requestParameter : requestParameters) {
                out.write(requestParameter.toString());
                out.write("&");
            }
            out.close();


            return new HttpResponse(httpConnection.getResponseCode());
        } catch (Exception e) {
            throw new HttpClientException(format("Problem posting to [%s]", urlString), e);
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


    private static void tryToDisconnect(HttpURLConnection httpConnection) {
        if (httpConnection == null) {
            return;
        }
        httpConnection.disconnect();
    }

    public HttpConsumer withRequestParameter(String parameterName, String parameterValue) {
        requestParameters.add(new RequestParameter(parameterName, parameterValue));
        return this;
    }


    private static class RequestParameter {
        private final String name;
        private final String value;

        private RequestParameter(String name, String value) {
            this.name = name;
            this.value = value;
        }

        public String name() {
            return name;
        }

        public String value() {
            return value;
        }

        public String toString() {
            return String.format("%s=%s", name, value);
        }
    }
}