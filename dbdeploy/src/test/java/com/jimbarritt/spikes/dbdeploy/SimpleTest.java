package com.jimbarritt.spikes.dbdeploy;

import org.apache.log4j.*;
import org.junit.*;

public class SimpleTest {
    private static final Logger log = Logger.getLogger(SimpleTest.class);

    @Test
    public void simpleTestForTheBuild() {
       log.info("this is a simple test to give Junit something to build in thebuild");         
    }
}