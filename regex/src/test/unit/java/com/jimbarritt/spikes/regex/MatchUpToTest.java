package com.jimbarritt.spikes.regex;

import org.junit.*;
import org.apache.log4j.*;

import java.util.regex.*;

public class MatchUpToTest {
    private static final Logger log = Logger.getLogger(MatchUpToTest.class);

    @Test
    public void matchesUpToAPoint() {
        String regex = "(.*)(\\..*)";

        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher("some/path/to/file.css");

        boolean matches = matcher.matches();
        log.info("Matches      : " + matches);
        log.info("Group Count  : " + matcher.groupCount());
        for (int i=0; i<=matcher.groupCount(); i++) {
            log.info("Group [" + i + "]  :" + matcher.group(i));
        }
    }
}
