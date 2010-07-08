package com.jimbarritt.spikes.regex;

import org.junit.*;

import java.util.regex.*;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class UrlSplitterTest {

    private static final String URL_SPLITTER_REGEX = "(http://[^/]*)(/.*)?";
    private Pattern pattern;

    @Before
    public void onceBeforeEachTest() {
        pattern = Pattern.compile(URL_SPLITTER_REGEX);
    }

    @Test
    public void splitsUrlWithServerAndPath() {
        Matcher matcher = pattern.matcher("http://www.google.com/some/path/to/file.css");
        
        MatcherDebug.printMatcher(matcher);
        assertThat(matcher.matches(), is(true));
        assertThat(matcher.group(1), is("http://www.google.com"));
        assertThat(matcher.group(2), is("/some/path/to/file.css"));
    }

    @Test
    public void splitsUrlWithJustServer() {
        Matcher matcher = pattern.matcher("http://www.google.com");

        MatcherDebug.printMatcher(matcher);
        assertThat(matcher.matches(), is(true));
        assertThat(matcher.group(1), is("http://www.google.com"));
        assertThat(matcher.group(2), is(nullValue()));
    }

    @Test
    public void splitsUrlWithJustServerAndTrailingSlash() {
        Matcher matcher = pattern.matcher("http://www.google.com/");

        MatcherDebug.printMatcher(matcher);
        assertThat(matcher.matches(), is(true));
        assertThat(matcher.group(1), is("http://www.google.com"));
        assertThat(matcher.group(2), is("/"));
    }

    @Test
    public void splitsUrlWithServerPortAndTrailingSlash() {
        Matcher matcher = pattern.matcher("http://www.google.com:8080/");

        MatcherDebug.printMatcher(matcher);
        assertThat(matcher.matches(), is(true));
        assertThat(matcher.group(1), is("http://www.google.com:8080"));
        assertThat(matcher.group(2), is("/"));
    }

    @Test
    public void splitsUrlWithServerAndPort() {
        Matcher matcher = pattern.matcher("http://www.google.com:8080");

        MatcherDebug.printMatcher(matcher);
        assertThat(matcher.matches(), is(true));
        assertThat(matcher.group(1), is("http://www.google.com:8080"));
        assertThat(matcher.group(2), is(nullValue()));
    }
}
