package com.jimbarritt.spikes.stringtemplate.volume;

import org.junit.*;

import static com.jimbarritt.spikes.stringtemplate.io.InlineStringTemplateRenderer.attribute;
import static com.jimbarritt.spikes.stringtemplate.io.InlineStringTemplateRenderer.renderStringTemplate;
import static com.jimbarritt.spikes.stringtemplate.volume.Volume.UnitOfMeasure.*;
import static org.hamcrest.core.Is.*;
import static org.junit.Assert.*;

public class VolumeAttributeRendererTest {

    @Test
    public void formatsInLitres() {
        String representation = renderStringTemplate("My car has an engine size of $engineSize;format=\"litres\"$",
                                                     attribute("engineSize", new Volume(20, LITRES)));

        assertThat(representation, is("My car has an engine size of 20.00 litres"));
    }

    @Test
    public void formatsInLitresWithShortFormat() {
        String representation = renderStringTemplate("My car has an engine size of $engineSize;format=\"litres-short\"$",
                                                attribute("engineSize", new Volume(20, LITRES)));

        assertThat(representation, is("My car has an engine size of 20.00l"));
    }


    @Test
    public void convertsToCc() {
        String representation = renderStringTemplate("My car has an engine size of $engineSize;format=\"cc\"$",
                                                        attribute("engineSize", new Volume(20, LITRES)));

        assertThat(representation, is("My car has an engine size of 20000 cubic centimetres"));
    }

    @Test
    public void formatsCcInShortFormat() {
        String representation = renderStringTemplate("My car has an engine size of $engineSize;format=\"cc-short\"$",
                                                        attribute("engineSize", new Volume(20, LITRES)));

        assertThat(representation, is("My car has an engine size of 20000cc"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void failsIfFormatNotRecognised() {
        renderStringTemplate("My car has an engine size of $engineSize;format=\"foobar\"$", attribute("engineSize", new Volume(20, LITRES)));
    }

}
