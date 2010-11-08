package org.jimbarritt.spikes.jvmencoding;

import org.junit.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

public class JvmEncodingDetectorTest {
    private String originalFileEncoding;

    @Before
    public void rememberFileEncodingBeforeTest() {
        this.originalFileEncoding = System.getProperty("file.encoding");
    }

    @After
    public void resetFileEncodingLikeBeforeTest() {
        System.setProperty("file.encoding", this.originalFileEncoding);        
    }

    @Test
    public void Describes_what_is_set_as_a_system_property() {
        System.setProperty("file.encoding", "IBM-01140");

        String description = new JvmEncodingDetector().describe();

        assertThat(description, containsString("-Dfile.encoding=IBM-01140"));
    }

    
}