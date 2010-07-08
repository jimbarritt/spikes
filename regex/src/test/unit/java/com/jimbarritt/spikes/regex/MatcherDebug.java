package com.jimbarritt.spikes.regex;

import org.apache.log4j.*;

import java.util.regex.*;

public class MatcherDebug {
    private static final Logger log = Logger.getLogger(MatcherDebug.class);

    public static void printMatcher(Matcher matcher) {
        boolean matches = matcher.matches();
        log.info("Matches      : " + matches);
        log.info("Group Count  : " + matcher.groupCount());
        for (int i = 0; i <= matcher.groupCount(); i++) {
            log.info("Group [" + i + "]  :" + matcher.group(i));
        }
    }
}