package com.jimbarritt.spikes.stringtemplate;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import org.antlr.stringtemplate.NoIndentWriter;
import org.antlr.stringtemplate.StringTemplate;
import org.antlr.stringtemplate.StringTemplateWriter;

public class StringTemplateRenderer {

    public String render(StringTemplate template) {
        try {
            final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            final Writer writer = new OutputStreamWriter(outputStream);
            try {
                final StringTemplateWriter templateWriter = new NoIndentWriter(writer);
                template.write(templateWriter);
                writer.flush();
                return outputStream.toString("UTF-8");
            } finally {
                writer.close();
            }
        } catch (IOException e) {
            throw new StringTemplateRenderingException("Failed to render template [" + template.getName() + "] (See cause for details)", e);
        }
    }

}
