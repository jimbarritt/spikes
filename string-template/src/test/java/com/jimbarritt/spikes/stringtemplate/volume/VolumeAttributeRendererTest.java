package com.jimbarritt.spikes.stringtemplate.volume;

import org.antlr.stringtemplate.*;
import org.junit.*;

import static com.jimbarritt.spikes.stringtemplate.volume.Volume.UnitOfMeasure.*;
import static org.hamcrest.core.Is.*;
import static org.junit.Assert.*;

public class VolumeAttributeRendererTest {

    @Test
    public void formatsInLitres() {
        String representation = renderStringTemplate("My car has an engine size of $engineSize;format=\"litres\"$",
                new Volume(20, LITRES));

        assertThat(representation, is("My car has an engine size of 20.00 litres"));
    }

    @Test
    public void formatsInLitresWithShortFormat() {
        String representation = renderStringTemplate("My car has an engine size of $engineSize;format=\"litres-short\"$",
                new Volume(20, LITRES));

        assertThat(representation, is("My car has an engine size of 20.00l"));
    }


    @Test
    public void convertsToCc() {
        String representation = renderStringTemplate("My car has an engine size of $engineSize;format=\"cc\"$",
                new Volume(20, LITRES));

        assertThat(representation, is("My car has an engine size of 20000 cubic centimetres"));
    }

    @Test
    public void formatsCcInShortFormat() {
        String representation = renderStringTemplate("My car has an engine size of $engineSize;format=\"cc-short\"$",
                new Volume(20, LITRES));

        assertThat(representation, is("My car has an engine size of 20000cc"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void failsIfFormatNotRecognised() {
        renderStringTemplate("My car has an engine size of $engineSize;format=\"foobar\"$", new Volume(20, LITRES));
    }

    private static String renderStringTemplate(String template, Volume volume) {
        StringTemplate stringTemplate = new StringTemplate(template);
        stringTemplate.registerRenderer(Volume.class, new VolumeAttributeRenderer());
        stringTemplate.setAttribute("engineSize", volume);
        return stringTemplate.toString();
    }

}
