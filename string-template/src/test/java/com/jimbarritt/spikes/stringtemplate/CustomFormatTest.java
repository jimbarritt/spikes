package com.jimbarritt.spikes.stringtemplate;

import com.jimbarritt.spikes.stringtemplate.volume.*;
import org.antlr.stringtemplate.*;
import org.junit.*;

public class CustomFormatTest {

    @Test
    public void canPassACustomFormat() {
        StringTemplate stringTemplate = new StringTemplate("My car has an engine size of $engineSize;format=\"litres\"$");

        stringTemplate.setAttribute("engineSize", new Volume(20, Volume.UnitOfMeasure.LITRES));
        stringTemplate.registerRenderer(Volume.class, new VolumeAttributeRenderer());
    }

}
