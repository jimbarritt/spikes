package com.jimbarritt.spikes.restfulie.io;

import java.io.*;

public class Iox {

    public static String readAsUtf8String(InputStream in) {
        byte[] buffer = new byte[255];
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            int bytesRead = in.read(buffer);
            while(bytesRead != -1)  {
                out.write(buffer, 0, bytesRead);
                out.flush();
                bytesRead = in.read(buffer);
            }
            return out.toString("UTF-8");
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
}