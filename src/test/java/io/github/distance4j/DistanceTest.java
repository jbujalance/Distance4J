package io.github.distance4j;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class DistanceTest {

    @Test
    public void testPlusNumber() {
        // GIVEN a distance
        Distance distance = Distance.ofMetres(10);

        // WHEN adding a number in another unit
        distance = distance.plus(50, LengthUnits.SI.DECIMETRE);

        // THEN the addition is correct
        assertEquals(15, distance.toMetres());
    }

    @Test
    public void testPlusDistance() {
        // GIVEN two distances
        Distance distMetres = Distance.ofMetres(10);
        Distance distKm = Distance.of(1, LengthUnits.SI.KILOMETRE);

        // WHEN adding the two distances
        Distance addition = distMetres.plus(distKm);

        // THEN the addition is correct
        assertEquals(1.010, addition.to(LengthUnits.SI.KILOMETRE));
    }

    @Test
    public void testMinusNumber() {
        // GIVEN a distance
        Distance distance = Distance.ofMetres(20);

        // WHEN subtracting a number
        distance = distance.minus(500, LengthUnits.SI.CENTIMETRE);

        // THEN the subtraction is correct
        assertEquals(15000, distance.to(LengthUnits.SI.MILLIMETRE));
    }

    @Test
    public void testMinusDistance() {
        // GIVEN two distances
        Distance distMm = Distance.of(10, LengthUnits.SI.MILLIMETRE);
        Distance distCm = Distance.of(1.5, LengthUnits.SI.CENTIMETRE);

        // WHEN subtracting the two distances
        Distance subtraction = distCm.minus(distMm);

        // THEN the subtraction is correct
        assertEquals(0.5, subtraction.to(LengthUnits.SI.CENTIMETRE));
    }

    @Test
    public void testCompareTo() {
        // GIVEN three distances
        Distance d1 = Distance.ofMetres(1);
        Distance d2 = Distance.of(1, LengthUnits.ImperialUS.YARD);
        Distance d3 = Distance.ofMetres(0.9144);

        // THEN the distances compare correctly
        assertThat(d1.compareTo(d2), greaterThan(0));
        assertThat(d2.compareTo(d1), lessThan(0));
        assertThat(d2.compareTo(d3), equalTo(0));
    }

    @Test
    public void testEquality_DifferentDistances() {
        // GIVEN two different distances
        Distance d1 = Distance.ofMetres(5);
        Distance d2 = Distance.of(10, LengthUnits.SI.HECTOMETRE);

        // THEN theirs hashes are different
        assertNotEquals(d1.hashCode(), d2.hashCode());

        // AND THEN they are not equals
        assertNotEquals(d1, d2);
    }

    @Test
    public void testEquality_EqualDistances() {
        // GIVEN two equal distances
        Distance d1 = Distance.of(1.23456789, LengthUnits.SI.KILOMETRE);
        Distance d2 = Distance.of(1234567.89, LengthUnits.SI.MILLIMETRE);

        // THEN their hashes are equal
        assertEquals(d1.hashCode(), d2.hashCode());

        // AND THEN the distances are equal
        assertEquals(d1, d2);
    }

    @Test
    public void testToString() {
        // GIVEN a distance
        Distance distance = Distance.of(6, LengthUnits.SI.DECAMETRE);

        // THEN the distance is correctly converted into a String
        assertEquals("60.0 metres", distance.toString());
    }
}
