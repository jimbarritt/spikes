package com.jimbarritt.spikes.stringtemplate.volume;

import org.antlr.stringtemplate.*;

import java.util.*;

import static com.jimbarritt.spikes.stringtemplate.volume.Volume.UnitOfMeasure.*;
import static com.jimbarritt.spikes.stringtemplate.volume.VolumeAttributeRenderer.FormattingAction.*;
import static java.lang.String.*;

public class VolumeAttributeRenderer implements AttributeRenderer {

    private static Map<String, FormattingAction> FORMATTING_ACTIONS = createFormattingActionMap();

    @Override public String toString(Object objectToFormat) {
        checkValueIsAVolume(objectToFormat);

        return objectToFormat.toString();
    }

    @Override public String toString(Object objectToFormat, String formatName) {
        checkValueIsAVolume(objectToFormat);

        if (!FORMATTING_ACTIONS.containsKey(formatName)) {
            throw new IllegalArgumentException(String.format("Unrecognised format [%s]", formatName));
        }

        return FORMATTING_ACTIONS.get(formatName).render((Volume) objectToFormat);
    }

    private static void checkValueIsAVolume(Object o) {
        if (!(o instanceof Volume)) {
            throw new IllegalArgumentException(format("Cannot render object: %s#%s", o, o.getClass().getSimpleName()));
        }
    }

    static abstract class FormattingAction {

        static Map<String, FormattingAction> createFormattingActionMap() {
            Map<String, FormattingAction> formattingActionMap = new HashMap<String, FormattingAction>();
            LITRES_FORMAT.registerIn(formattingActionMap);
            LITRES_SHORT_FORMAT.registerIn(formattingActionMap);
            CC_FORMAT.registerIn(formattingActionMap);
            CC_SHORT_FORMAT.registerIn(formattingActionMap);
            return formattingActionMap;
        }

        abstract String render(Volume volume);

        private final String formatString;
        private final Volume.UnitOfMeasure unitOfMeasure;

        protected FormattingAction(String formatString, Volume.UnitOfMeasure unitOfMeasure) {
            this.formatString = formatString;
            this.unitOfMeasure = unitOfMeasure;
        }

        static FormattingAction LITRES_FORMAT = new FormattingAction("litres", LITRES) {
            @Override public String render(Volume volume) {
                return renderVolumeWithLongName(volume);
            }
        };

        static FormattingAction LITRES_SHORT_FORMAT = new FormattingAction("litres-short", LITRES) {
            @Override public String render(Volume volume) {
                return renderVolumeWithShortName(volume);
            }
        };

        static FormattingAction CC_FORMAT = new FormattingAction("cc", CC) {
            @Override public String render(Volume volume) {
                return renderVolumeWithLongName(volume);
            }
        };

        static FormattingAction CC_SHORT_FORMAT = new FormattingAction("cc-short", CC) {
            @Override public String render(Volume volume) {
                return renderVolumeWithShortName(volume);
            }
        };

        public void registerIn(Map<String, FormattingAction> map) {
            map.put(formatString, this);
        }

        protected String renderVolumeWithLongName(Volume inputVolume) {
            return format("%s %s", inputVolume.in(unitOfMeasure), unitOfMeasure.longName());
        }

        protected String renderVolumeWithShortName(Volume inputVolume) {
            return format("%s%s", inputVolume.in(unitOfMeasure), unitOfMeasure.shortName());
        }

    }
}