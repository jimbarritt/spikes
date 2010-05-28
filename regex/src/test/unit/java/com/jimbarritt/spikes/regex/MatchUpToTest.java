package com.jimbarritt.spikes.regex;

import org.apache.log4j.*;
import org.junit.*;

import java.util.regex.*;

public class MatchUpToTest {
    private static final Logger log = Logger.getLogger(MatchUpToTest.class);

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

    public static void printMatcher(Matcher matcher) {
        boolean matches = matcher.matches();
        log.info("Matches      : " + matches);
        log.info("Group Count  : " + matcher.groupCount());
        for (int i = 0; i <= matcher.groupCount(); i++) {
            log.info("Group [" + i + "]  :" + matcher.group(i));
        }
    }
}
