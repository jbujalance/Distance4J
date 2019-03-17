package io.github.distance4j;

import java.math.BigDecimal;

/**
 * A unit of length.
 * A length unit must provide an equivalent value in the reference unit (the metre).
 */
@FunctionalInterface
public interface LengthUnit {

    /**
     * This method provides the equivalent magnitude in meters of one given unit.
     * A metre is defined as the length of the path travelled by light in vacuum during a time interval of ​1/299792458 seconds.
     * @return The equivalent number of meters
     */
    BigDecimal toMetres();

    /**
     * Provides an equivalence in metres to the given magnitude of this unit.
     * @param magnitude The magnitude of this unit to convert to metres
     * @return The equivalent magnitude in metres.
     */
    default BigDecimal toMetres(final Number magnitude) {
        return BigDecimal.valueOf(magnitude.doubleValue()).multiply(toMetres());
    }

    /**
     * Given a magnitude in metres, provides an equivalent magnitude in this unit.
     * @param metres The magnitude in metres to convert to this unit.
     * @return The equivalent magnitude in this unit from the given metres.
     */
    default BigDecimal fromMetres(final BigDecimal metres) {
        return metres.divide(toMetres());
    }

}
