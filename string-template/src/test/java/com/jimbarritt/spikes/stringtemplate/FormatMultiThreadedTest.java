package com.jimbarritt.spikes.stringtemplate;

import org.antlr.stringtemplate.*;
import org.apache.log4j.*;
import org.junit.*;

import java.util.*;

import static java.lang.String.*;

public class FormatMultiThreadedTest {
    private static final Logger log = Logger.getLogger(FormatMultiThreadedTest.class);
    
    @Test
    public void formatsTheFormatNameWhenMultiThreaded() {
        final int NUM_THREADS = 10;
        final int NUM_RENDERS = 50;
        ThreadGroup tg = new ThreadGroup("test-st");

        StringTemplate st = new StringTemplate("This is a title cased value : [$value;format=\"title\"$]");
        st.registerRenderer(String.class, new FormatAttributeRenderer());

        for (int i = 0; i < NUM_THREADS; ++i) {
            new Thread(tg, new RenderStringTemplate(NUM_RENDERS, st), format("thread-%d", i)).start();
        }

        while (tg.activeCount() > 0) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }

    public static class FormatAttributeRenderer implements AttributeRenderer {
        public FormatAttributeRenderer() {
        }

        @Override
        public String toString(Object o) {
            return (o == null) ? null : o.toString();
        }

        @Override
        public String toString(Object o, String formatName) {
            log.debug(format("toString(Object %s, String %s)", o, formatName));
            if (o == null) {
                return null;
            }
            if ("Title".equals(formatName)) {
                log.warn("!!!!! Being called with title cased format!");
            }
            if ("title".equals(formatName)) {
                return titleCase(o.toString());
            }
            return o.toString();
        }


        public static String titleCase(String input) {
            if ("title".equals(input)) {
                log.warn("Title-casing 'title'!!");
            }
            String[] words = input.split(" ");
            StringBuilder sb = new StringBuilder();
            for (String word : words) {
                String firstChar = String.valueOf(word.charAt(0)).toUpperCase();
                sb.append(firstChar);
                if (word.length() > 1) {
                    sb.append(word.substring(1));
                }
                if (!word.equals(words[words.length - 1])) {
                    sb.append(" ");
                }
            }
            String output = sb.toString();
            log.debug(format("Title-cased [%s] to [%s]", input, output));
            return output;
        }
    }


    private static class RenderStringTemplate implements Runnable {
        private final Random random = new Random();
        private final int numberOfRenders;
        private final StringTemplate st;

        public RenderStringTemplate(int numberOfRenders, StringTemplate st) {
            this.numberOfRenders = numberOfRenders;
            this.st = st;
        }

        @Override
        public void run() {
            for (int i = 0; i < numberOfRenders; ++i) {
                st.toString();
                try {
                    Thread.sleep((long) (100 * random.nextFloat()));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

        }
    }
}