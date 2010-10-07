package com.jimbarritt.spikes.stringtemplate;

import com.jimbarritt.spikes.stringtemplate.io.*;
import org.antlr.stringtemplate.*;
import org.apache.log4j.*;
import org.junit.*;

import static com.jimbarritt.spikes.stringtemplate.CustomFormatTest.FormatAttributeRenderer.titleCase;
import static java.lang.String.format;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class CustomFormatTest {
    private static final Logger log = Logger.getLogger(CustomFormatTest.class);

    private InlineStringTemplateRenderer render;


    @Before
    public void setUp() {
        render = new InlineStringTemplateRenderer();
    }

    @Test
    public void titleCaseAString() {
        String input = "this will be a title-cased sentence";

        String output = titleCase(input);

        assertThat(output, is("This Will Be A Title-cased Sentence"));
    }

    @Test
    public void usesACustomFormat() {
        String template = "This is a title cased value : [$value;format=\"title\"$]";

        String representation = render.template(template)
                                      .format(String.class).with(new FormatAttributeRenderer())
                                      .with().attribute("value", "something to title-case")
                                      .toString();


        assertThat(representation, is("This is a title cased value : [Something To Title-case]"));
    }

    @Test
    public void canRenderFromWithinItself() {
        StringTemplate st = new StringTemplate("This is a title cased value : [$value;format=\"title\"$]");

        st.registerRenderer(String.class, new FormatAttributeRenderer(st));
        st.setAttribute("value", "something to title-case");

        String representation = st.toString();

        assertThat(representation, is("This is a title cased value : [Something To Title-case]"));
    }


    public static class FormatAttributeRenderer implements AttributeRenderer {
        private StringTemplate st;
        boolean isRenderingSelf = false;

        public FormatAttributeRenderer() {
        }

        public FormatAttributeRenderer(StringTemplate st) {
            this.st = st;
        }

        @Override
        public String toString(Object o) {
            return (o == null) ? null : o.toString();
        }

        @Override
        public String toString(Object o, String formatName) {
            if (st !=null && !isRenderingSelf) {
                isRenderingSelf = true;
                log.debug(format("The string template rendering me is [%s]", st.toString()));
                isRenderingSelf = false;
            }

            log.debug(format("toString(Object %s, String %s)", o, formatName));
            if (o == null) {
                return null;
            }
            if ("Title".equals(formatName)) {
                throw new IllegalArgumentException("Somehow we title-cased the format name!");
            }
            if ("title".equals(formatName)) {
                return titleCase(o.toString());
            }
            return (null);
        }

        public static String titleCase(String input) {
            String[] words = input.split(" ");
            StringBuilder sb = new StringBuilder();
            for (String word : words) {
                String firstChar = String.valueOf(word.charAt(0)).toUpperCase();
                sb.append(firstChar);
                if (word.length() > 1) {
                    sb.append(word.substring(1));
                }
                if (!word.equals(words[words.length-1])) {
                    sb.append(" ");
                }
            }
            String output = sb.toString();
            log.debug(format("Title-cased [%s] to [%s]", input, output));
            return output;
        }
    }
}