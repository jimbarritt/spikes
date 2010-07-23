package com.jimbarritt.spikes.stringtemplate.volume;

import com.jimbarritt.spikes.stringtemplate.io.*;
import org.junit.*;

import static com.jimbarritt.spikes.stringtemplate.volume.Volume.UnitOfMeasure.*;
import static org.hamcrest.core.Is.*;
import static org.junit.Assert.*;

public class VolumeAttributeRendererTest {

    InlineStringTemplateRenderer render;

    @Before
    public void onceBeforeAllTests() {
        render = new InlineStringTemplateRenderer();
    }

    @Test
    public void formatsInLitres() {
        String representation = render.template("My car has an engine size of $engineSize;format=\"litres\"$")
                                        .with().attribute("engineSize", new Volume(20, LITRES))
                                      .toString();

        assertThat(representation, is("My car has an engine size of 20.00 litres"));
    }

    @Test
    public void formatsInLitresWithShortFormat() {
        String representation = render.template("My car has an engine size of $engineSize;format=\"litres-short\"$")
                                         .with().attribute("engineSize", new Volume(20, LITRES))
                                         .toString();

        assertThat(representation, is("My car has an engine size of 20.00l"));
    }


    @Test
    public void convertsToCc() {
        String representation = render.template("My car has an engine size of $engineSize;format=\"cc\"$")
                                         .with().attribute("engineSize", new Volume(20, LITRES))
                                         .toString();

        assertThat(representation, is("My car has an engine size of 20000 cubic centimetres"));
    }

    @Test
    public void formatsCcInShortFormat() {
        String representation = render.template("My car has an engine size of $engineSize;format=\"cc-short\"$")
                                         .with().attribute("engineSize", new Volume(20, LITRES))
                                         .toString();

        assertThat(representation, is("My car has an engine size of 20000cc"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void failsIfFormatNotRecognised() {
        render.template("My car has an engine size of $engineSize;format=\"foobar\"$")
                 .with().attribute("engineSize", new Volume(20, LITRES))
                 .toString();
    }

}
