package com.jimbarritt.spikes.stringtemplate.io;

import org.antlr.stringtemplate.*;
import org.junit.*;

import java.io.*;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class InterfaceStringTemplateGroupFactoryTest {

    @Test
    public void createsInterfaceBasedGroup() {
        InterfaceStringTemplateGroupFactory factory = new InterfaceStringTemplateGroupFactory();

        StringTemplateGroup group = factory.createGroupFromRootPath(StringTemplateRootPath.getStringTemplateRootDir(), StringTemplateGroup.DEFAULT_ERROR_LISTENER);

        assertThat(group, is(instanceOf(InterfaceBasedStringTemplateGroup.class)));
    }

    @Test
    public void createsInterfaceBasedGroupFromReader() {
        String groupContents = "group someGroup;";

        InterfaceStringTemplateGroupFactory factory = new InterfaceStringTemplateGroupFactory();

        StringTemplateGroup group = factory.createGroupFromReader(new StringReader(groupContents), StringTemplateGroup.DEFAULT_ERROR_LISTENER);

        assertThat(group, is(instanceOf(InterfaceBasedStringTemplateGroup.class)));
    }
}