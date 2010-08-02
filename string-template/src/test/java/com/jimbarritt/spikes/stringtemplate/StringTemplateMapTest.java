package com.jimbarritt.spikes.stringtemplate;

import com.jimbarritt.spikes.stringtemplate.collection.*;
import com.jimbarritt.spikes.stringtemplate.io.*;
import org.antlr.stringtemplate.*;
import org.apache.log4j.*;
import org.junit.*;

import static org.junit.Assert.assertThat;
import static org.junit.internal.matchers.StringContains.containsString;

public class StringTemplateMapTest {
    private static final Logger log = Logger.getLogger(StringTemplateMapTest.class);

    private Log4jStringTemplateErrorListener errorListener;

    @Before
    public void onceBeforeEachTest() {
        errorListener = new Log4jStringTemplateErrorListener();
    }

    @Test
    public void canUseAMap() {
        StringTemplate st = new StringTemplate("This is a value from the map $someMap.(\"someValue\")$");
        st.setErrorListener(errorListener);
        st.setAttribute("someMap", new SpecialMap());

        String result = new StringTemplateRenderer().render(st);

        assertThat(result, containsString("special.someValue"));
    }

    @Test
    public void alsoWorksIfYouDontExplicitlyUseTheParentheses() {
        StringTemplate st = new StringTemplate("This is a value from the map $someMap.someValue$");
        st.setErrorListener(errorListener);
        st.setAttribute("someMap", new SpecialMap());

        String result = new StringTemplateRenderer().render(st);

        assertThat(result, containsString("special.someValue"));
    }

    @Test
    public void doubleMapLevel() {
        StringTemplate st = new StringTemplate("This is a value from the map $someMap.level1.level2$");
        st.setErrorListener(errorListener);
        st.setAttribute("someMap", new SpecialLevelMap());

        String result = new StringTemplateRenderer().render(st);

        assertThat(result, containsString("level1.special.level2"));
    }


    private static class SpecialMap extends UnsupportedOperationMap<String, String> {

        private final String prefix;

        public SpecialMap() {
            this("");
        }

        public SpecialMap(String prefix) {
            this.prefix = prefix;
        }


        @Override public boolean containsKey(Object key) {
            return true;
        }

        @Override public String get(Object key) {
            return prefix + "special." + key;
        }
    }

    private static class SpecialLevelMap extends UnsupportedOperationMap<String, SpecialMap> {

        @Override public boolean containsKey(Object key) {
            return true;
        }

        @Override public SpecialMap get(Object key) {
            return new SpecialMap("level1.");
        }
    }
}