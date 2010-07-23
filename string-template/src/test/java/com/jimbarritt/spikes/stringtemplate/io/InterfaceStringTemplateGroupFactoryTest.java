package com.jimbarritt.spikes.stringtemplate.io;

import org.antlr.stringtemplate.*;
import org.junit.*;

import java.io.*;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class InterfaceStringTemplateGroupFactoryTest {
    private Log4jStringTemplateErrorListener errorListener;

    @Before
    public void onceBeforeEachTest() {
        errorListener = new Log4jStringTemplateErrorListener();
    }

    @Test
    public void createsInterfaceBasedGroup() {
        InterfaceStringTemplateGroupFactory factory = new InterfaceStringTemplateGroupFactory();

        errorListener = new Log4jStringTemplateErrorListener();
        StringTemplateGroup group = factory.createGroupFromRootPath(StringTemplateRootPath.getStringTemplateRootDir(), errorListener);

        assertThat(group, is(instanceOf(InterfaceBasedStringTemplateGroup.class)));
    }

    @Test
    public void createsInterfaceBasedGroupFromReader() {
        String groupContents = "group someGroup; toCSVRow(object) ::= <<$it$>>";

        InterfaceStringTemplateGroupFactory factory = new InterfaceStringTemplateGroupFactory();

        StringTemplateGroup group = factory.createGroupFromReader(new StringReader(groupContents), errorListener);

        assertThat(group, is(instanceOf(InterfaceBasedStringTemplateGroup.class)));
    }
}