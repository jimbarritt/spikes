package com.jimbarritt.spikes.regex;

import org.junit.*;

import java.util.regex.*;

import static com.jimbarritt.spikes.regex.MatcherDebug.*;

public class MatchHyphenatedString {

    @Test
    public void matchesLastHyphenatedParamter() {
        String regex = "(.*)-(.*)(\\..*)";

        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher("some/path/to/somthing-with-hyphens-init-file.css");

        printMatcher(matcher);
    }

}