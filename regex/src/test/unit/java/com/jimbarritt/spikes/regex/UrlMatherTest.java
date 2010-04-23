package com.jimbarritt.spikes.regex;

import org.junit.*;

import java.util.regex.*;

import static com.jimbarritt.spikes.regex.MatchUpToTest.printMatcher;

public class UrlMatherTest {

    @Test
    public void matchesUpToAPoint() {
        String regex = "(http://[^/]*)(/.*)";

        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher("http://www.google.com/some/path/to/file.css");

        printMatcher(matcher);
    }
}
