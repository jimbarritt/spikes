package com.jimbarritt.spikes.stringtemplate;

import com.jimbarritt.spikes.stringtemplate.volume.*;
import org.antlr.stringtemplate.*;
import org.junit.*;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class CustomFormatTest {

    @Test
    public void canPassACustomFormat() {
        StringTemplate stringTemplate = new StringTemplate("My car has an engine size of $engineSize;format=\"litres\"$");

        stringTemplate.registerRenderer(Volume.class, new VolumeAttributeRenderer());
        stringTemplate.setAttribute("engineSize", new Volume(20, Volume.UnitOfMeasure.LITRES));

        String representation = stringTemplate.toString();
        assertThat(representation, is("My car has an engine size of 20.00 litres"));
    }

}
