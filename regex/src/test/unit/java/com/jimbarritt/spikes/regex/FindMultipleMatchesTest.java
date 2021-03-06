package com.jimbarritt.spikes.regex;

import org.junit.*;
import org.apache.log4j.*;

import java.util.regex.*;

import static java.util.regex.Pattern.compile;

public class FindMultipleMatchesTest {

    private static final Logger log = Logger.getLogger(FindMultipleMatchesTest.class);

    @Test
    public void findsMultipleGroups() {
        Pattern pattern = compile("\\w* ");
        
        Matcher matcher = pattern.matcher("this is a sentance");
        log.info("GroupCount: " + matcher.groupCount());
        while (matcher.find()) {
            log.info("Found group : [" + matcher.group().trim() + "]");
        }
    }

    @Test
    public void findStructuredGroups() {
        Pattern pattern = compile("\\{\\w*\\}");
        Matcher matcher = pattern.matcher("/{parameter1}/some/path/{parameter2}/end");
        while(matcher.find()) {
            log.info("Found parameter: [" + matcher.group().replaceAll("\\{|\\}", "") + "]");
        }
        String output = matcher.replaceAll("(.*)");
        log.info("Replaced output: [" + output + "]");
    }

    @Test
    public void findStructuredGroupsWithParenthesis() {
        Pattern pattern = compile("\\([^\\)]*\\)");
        Matcher matcher = pattern.matcher("Some sentance (parameter 1d) /some/path/ (parameter 2 ) /end");
        while(matcher.find()) {
            log.info("Found parameter: [" + matcher.group().replaceAll("\\(|\\)", "") + "]");
        }
        String output = matcher.replaceAll("(.*)");
        log.info("Replaced output: [" + output + "]");
    }
}
