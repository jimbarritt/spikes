package com.jimbarritt.spikes.regex;

import org.junit.*;

import java.util.regex.*;

import static com.jimbarritt.spikes.regex.MatcherDebug.*;

public class MatchUpToTest {

    @Test
    public void matchesUpToAPoint() {
        String regex = "(.*)(\\..*)";

        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher("some/path/to/file.css");

        printMatcher(matcher);
    }

    @Test
    public void optionallyMatchAGroup() {
        String regex = "/([^.]*)(\\..*)?";

        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher("/some/path/to/file");
        printMatcher(matcher);

        matcher = pattern.matcher("/some/path/to/file.css");
        printMatcher(matcher);
    }

    @Test
    public void matchesOptionalStuff() {
        String regex = "(<hr)( +SIZE *= *[0-9]+)? *";

        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher("<hr SIZE=23 ");
        printMatcher(matcher);

        matcher = pattern.matcher("<hr ");
        printMatcher(matcher);

    }

}
