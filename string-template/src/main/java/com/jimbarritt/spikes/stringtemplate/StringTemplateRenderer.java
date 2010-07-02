package com.jimbarritt.spikes.stringtemplate;

import org.antlr.stringtemplate.*;

import java.io.*;

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
        } catch (Exception e) {
            throw new StringTemplateRenderingException("Failed to render template [" + template.getName() + "] (See cause for details)", e);
        }
    }

}
