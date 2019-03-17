package io.github.distance4j;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * A distance measure such as 15 Km.
 * <p>
 *     The Distance object is unit-agnostic, wrapping an internal value converted in metres.
 *     This class provides several conversion and arithmetic operation to execute on a Distance
 *     allowing to operate with any unit and converting the final result to any unit.
 * </p>
 * <p>
 *     Internally, the Distance class is backed by a {@link BigDecimal} instance to ensure
 *     an accurate treatment of the decimal points.
 * </p>
 * <p>
 *     This is a <a href="https://docs.oracle.com/javase/8/docs/api/java/lang/doc-files/ValueBased.html">value-based</a> class.
 * </p>
 */
public class Distance implements Comparable<Distance> {

    /** Distance of 0. */
    public static final Distance ZERO = new Distance(BigDecimal.ZERO);

    private BigDecimal metres;

    private Distance(final BigDecimal magnitude) {
        this.metres = magnitude;
    }

    private Distance(final Number magnitude) {
        this.metres = BigDecimal.valueOf(magnitude.doubleValue());
    }

    /**
     * Factory method to create a distance corresponding to the given magnitude and unit.
     * @param magnitude The magnitude of the measured distance.
     * @param unit The unit of the measurement.
     * @return A Distance holding the given measurement, abstracting the unit.
     */
    public static Distance of(final Number magnitude, final LengthUnit unit) {
        // TODO null checks
        BigDecimal metres = unit.toMetres(magnitude);
        return Distance.ofMetres(metres);
    }

    /**
     * Factory method to create a distance from a magnitude measured in metres.
     * @param metres The distance magnitude in metres.
     * @return A Distance holding the given measurement, abstracting the unit.
     */
    public static Distance ofMetres(final Number metres) {
        return new Distance(metres);
    }

    /**
     * Returns a new distance instance holding the addition of this distance and the given measure.
     * @param augend The magnitude of the distance to add.
     * @param unit The unit of the distance to add.
     * @return A new distance instance holding the addition of this distance and the given measure.
     */
    public Distance plus(final Number augend, final LengthUnit unit) {
        BigDecimal addition = metres.add(unit.toMetres(augend));
        return Distance.ofMetres(addition);
    }

    /**
     * Adds the given distance to this distance.
     * @param augend The distance to add.
     * @return A new distance instance holding the addition of this distance and the given one.
     */
    public Distance plus (final Distance augend) {
        BigDecimal addition = metres.add(augend.metres);
        return Distance.ofMetres(addition);
    }

    /**
     * Subtracts the given distance measure to this distance.
     * @param subtrahend The magnitude of the distance to subtract.
     * @param unit The unit of the subtrahend measurement.
     * @return A new instance of distance holding the subtraction.
     */
    public Distance minus(final Number subtrahend, final LengthUnit unit) {
        BigDecimal subtraction = metres.subtract(unit.toMetres(subtrahend));
        return Distance.ofMetres(subtraction);
    }

    /**
     * Subtracts the given distance to this distance.
     * @param subtrahend The distance to subtract.
     * @return A new instance of distance holding the subtraction of this distance and the given one.
     */
    public Distance minus(final Distance subtrahend) {
        BigDecimal subtraction = metres.subtract(subtrahend.metres);
        return Distance.ofMetres(subtraction);
    }

    /**
     * Unwraps the distance magnitude converting it to the given unit.
     * @param unit He length unit to convert the distance magnitude to.
     * @return The converted distance magnitude as a primitive double.
     */
    public double to(final LengthUnit unit) {
        return unit.fromMetres(metres).doubleValue();
    }

    /**
     * Unwraps the distance magnitude converting it to metres.
     * @return THe distance magnitude in metres as a primitive double.
     */
    public double toMetres() {
        return metres.doubleValue();
    }

    @Override
    public int hashCode() {
        return metres.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Distance distance = (Distance) o;
        return Objects.equals(metres, distance.metres);
    }

    @Override
    public String toString() {
        return metres.toString() + " metres";
    }

    @Override
    public int compareTo(final Distance other) {
        return metres.compareTo(other.metres);
    }
}
