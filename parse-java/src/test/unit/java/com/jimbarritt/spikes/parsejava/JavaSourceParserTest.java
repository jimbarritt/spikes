package com.jimbarritt.spikes.parsejava;

import org.junit.*;

import java.io.*;
import java.util.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.MockitoAnnotations.initMocks;

public class JavaSourceParserTest {

    @Before
    public void setup() {
        initMocks(this);
    }

    @Test
    public void extractsJavaClass() {
        final String source = "package com.foo.bar; public class TestClass {}";
        Reader reader = new BufferedReader(new StringReader(source));

        final JavaSourceParser parser = new JavaSourceParser();
        SourceFile sourceFile = readFrom(reader, new ReadOperation<SourceFile>(){
            @Override
            public SourceFile readFrom(Reader reader) {
                return parser.parse("/file/path", "fileName.txt", reader);
            }
        });

        assertThat(sourceFile.sourceLinesOfCode(), is(2L));
        List<SourceClass> sourceClasses = sourceFile.getSourceClasses();
        assertThat(sourceClasses.size(), is(1));
        assertThat(sourceClasses.get(0).getFullyQualifiedName(), is("com.foo.bar.TestClass"));

    }

    private static interface ReadOperation<T> {
        T readFrom(Reader reader);
    }

    private static <T> T readFrom(Reader reader, ReadOperation<T> readOperation) {
        try {
            return readOperation.readFrom(reader);
        } finally {
            tryToClose(reader);
        }
    }

    private static void tryToClose(Reader reader) {
        if (reader == null) {
            return;
        }
        try {
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException("Could not close reader (See Cause)", e);
        }
    }


}