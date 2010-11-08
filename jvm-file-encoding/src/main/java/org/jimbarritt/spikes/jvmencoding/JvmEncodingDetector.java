package org.jimbarritt.spikes.jvmencoding;

import static java.lang.String.format;

public class JvmEncodingDetector {

    public JvmEncodingDetector() {
    }

    public String describe() {
        StringBuilder sb = new StringBuilder();

        sb.append(format("-Dfile.encoding=%s", fileEncodingFromSystemProperty()));

        return sb.toString();
    }

    private String fileEncodingFromSystemProperty() {
        return System.getProperty("file.encoding");
    }
}