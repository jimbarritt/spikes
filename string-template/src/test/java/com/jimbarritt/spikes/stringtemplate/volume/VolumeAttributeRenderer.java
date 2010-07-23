package com.jimbarritt.spikes.stringtemplate.volume;

import org.antlr.stringtemplate.*;

import static com.jimbarritt.spikes.stringtemplate.volume.Volume.UnitOfMeasure.CC;
import static com.jimbarritt.spikes.stringtemplate.volume.Volume.UnitOfMeasure.LITRES;
import static java.lang.String.format;

public class VolumeAttributeRenderer implements AttributeRenderer {
    @Override public String toString(Object o) {
        checkValueIsAVolume(o);
        return o.toString();
    }

    @Override public String toString(Object o, String formatName) {
        checkValueIsAVolume(o);
        Volume volume = (Volume)o;

        if ("litres".equals(formatName)) {
            return renderVolumeWithLongName(volume, LITRES);
        } else if ("cc".equals(formatName)) {
            return renderVolumeWithLongName(volume, CC);
        }

        throw new IllegalArgumentException(String.format("Unrecognised format [%s]", formatName));
    }

    private static String renderVolumeWithLongName(Volume inputVolume, Volume.UnitOfMeasure unitOfMeasure) {
        return format("%s %s", inputVolume.in(unitOfMeasure), unitOfMeasure.longName());        
    }

    private static void checkValueIsAVolume(Object o) {
        if (!(o instanceof Volume)) {
            throw new IllegalArgumentException(format("Cannot render object: %s#%s", o, o.getClass().getSimpleName()));
        }
    }
}