package com.jimbarritt.spikes.stringtemplate;

import com.jimbarritt.spikes.stringtemplate.io.*;
import org.antlr.stringtemplate.*;
import org.junit.*;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class CustomFormatTest {
    private InlineStringTemplateRenderer render;

    @Before
    public void setUp() {
        render = new InlineStringTemplateRenderer();
    }

    @Test
    public void titleCaseAString() {
        String input = "this will be a title-cased sentance";

        String output = FormatAttributeRenderer.titleCase(input);

        assertThat(output, is("This Will Be A Title-cased Sentance"));
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



    private static class FormatAttributeRenderer implements AttributeRenderer {
        @Override public String toString(Object o) {
            return (o == null) ? null : o.toString();
        }

        @Override public String toString(Object o, String formatName) {
            if (o == null) {
                return null;
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
            return sb.toString();
        }
    }
}