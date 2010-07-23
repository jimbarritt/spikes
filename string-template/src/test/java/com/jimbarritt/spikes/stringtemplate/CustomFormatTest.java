package com.jimbarritt.spikes.stringtemplate;

import com.jimbarritt.spikes.stringtemplate.volume.*;
import org.antlr.stringtemplate.*;
import org.junit.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class CustomFormatTest {

    @Test
    public void canFormatInLitres() {
        String representation = renderStringTemplate("My car has an engine size of $engineSize;format=\"litres\"$",
                                                     new Volume(20, Volume.UnitOfMeasure.LITRES));

        assertThat(representation, is("My car has an engine size of 20.00 litres"));
    }

    @Test
    public void canConvertToCc() {
        String representation = renderStringTemplate("My car has an engine size of $engineSize;format=\"cc\"$",
                                                     new Volume(20, Volume.UnitOfMeasure.LITRES));

        System.out.println("[" + representation + "]");

        assertThat(representation.equalsIgnoreCase("My car has an engine size of 20000 cubic centimetres"), is(true));
        assertEquals("My car has an engine size of 20000 cubic centimetres", representation);
        assertThat(representation, is("My car has an engine size of 20000 cubic centimetres"));
        assertThat(representation.length(), is("My car has an engine size of 20000 cubic centimeters".length()));
        String expected = "My car has an engine size of 20000 cubic centimeters";
        for (int iChar=0; iChar<representation.length();++iChar) {
            if (representation.charAt(iChar) != expected.charAt(iChar)) {
                System.out.println("Did not match char [" + iChar + "] " + representation.charAt(iChar) + " != " + expected.charAt(iChar));
            }
        }
        assertThat(representation, is(expected));
    }

    private static String renderStringTemplate(String template, Volume volume) {
        StringTemplate stringTemplate = new StringTemplate(template);
        stringTemplate.registerRenderer(Volume.class, new VolumeAttributeRenderer());
        stringTemplate.setAttribute("engineSize", volume);
        return stringTemplate.toString();        
    }

}
