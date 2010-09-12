package com.jimbarritt.spikes.restfulie.server.domain;

import java.text.*;
import java.util.*;

public class LocationDescription {

    public static LocationDescription simpleDescription(String description) {
        return new LocationDescription().appendText(description);
    }

    StringBuilder sb = new StringBuilder();
    public LocationDescription appendText(String text) {
        sb.append(text);
        return this;
    }

    public String toString() {
        return sb.toString();
    }

    public String toString(List<ExitTo> exitTos) {
        String text = sb.toString();
        List<String> parameters = new ArrayList<String>();
        for (ExitTo exitTo : exitTos) {
            parameters.add(exitTo.describe());
        }

        return MessageFormat.format(text, parameters.toArray(new String[0]));
    }
}