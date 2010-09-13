package com.jimbarritt.spikes.restfulie.client.domain;

import java.util.*;
import java.util.regex.*;

public class LocationDescriptionParser {

    public String[] parseDescription(String description) {
        Pattern pattern = Pattern.compile("\\([^\\)]*\\)");
        Matcher matcher = pattern.matcher(description);

        List<String> descriptions = new ArrayList<String>();
        while(matcher.find()) {
            descriptions.add(matcher.group().replaceAll("\\(|\\)", ""));
        }

        return descriptions.toArray(new String[0]);
    }

}