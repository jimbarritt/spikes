package com.jimbarritt.spikes.stringtemplate.volume;

import java.text.*;

public class Volume {
    private float value;
    private final UnitOfMeasure unitOfMeasure;

    public Volume(float value, UnitOfMeasure unitOfMeasure) {
        this.value = value;
        this.unitOfMeasure = unitOfMeasure;
    }

    public Volume in(UnitOfMeasure newUnitOfMeasure) {
        return newUnitOfMeasure.convert(this);
    }   

    public String toString() {
        return unitOfMeasure.format(this);
    }

    public static abstract class UnitOfMeasure {

        private final DecimalFormat decimalFormat;
        private final String longName;
        private final String shortName;

        public UnitOfMeasure(DecimalFormat decimalFormat, String longName, String shortName) {
            this.decimalFormat = decimalFormat;
            this.longName = longName;
            this.shortName = shortName;
        }

        public abstract Volume convert(Volume volume);

        public String format(Volume volume) {
            return decimalFormat.format(volume.value);
        }

        public String longName() {
            return longName;
        }

        public String shortName() {
            return shortName;
        }

        public static UnitOfMeasure LITRES = new UnitOfMeasure(new DecimalFormat("0.00"), "litres", "l") {
            @Override public Volume convert(Volume volume) {
                if (LITRES == volume.unitOfMeasure) {
                    return volume;
                }
                if (CC == volume.unitOfMeasure) {
                    return new Volume(volume.value / 1000, CC);
                }
                throw new IllegalArgumentException("Cannot Convert Volume " + volume + " to litres");
            }
        };

        public static UnitOfMeasure CC = new UnitOfMeasure(new DecimalFormat("0"), "cubic centimetres", "cc") {
            @Override public Volume convert(Volume volume) {
                if (CC == volume.unitOfMeasure) {
                    return volume;
                }
                if (LITRES == volume.unitOfMeasure) {
                    return new Volume(volume.value * 1000, CC);
                }
                throw new IllegalArgumentException("Cannot Convert Volume " + volume + " to cc");
            }
        };
    }

}