package com.jimbarritt.spikes.regex;

import org.junit.*;

import java.util.regex.*;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class IntegrationTestNameFilterTest {

    /**
     * <a href="http://stackoverflow.com/questions/406230/regular-expression-to-match-string-not-containing-a-word">See
     * this doc on Stack Overflow</a>
     */
    @Test
    public void matchesUnitTestsButNotIntegrationTests() {
        String expression = "(.(?!Integration))*Test";
        Pattern pattern = Pattern.compile(expression);

        assertThat(pattern.matcher("SomeUnitTest").matches(), is(true));
        assertThat(pattern.matcher("SomeIntegrationTest").matches(), is(false));
        assertThat(pattern.matcher("SomeNonTestClass").matches(), is(false));
    }

}