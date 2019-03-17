package io.github.distance4j;

import java.math.BigDecimal;

/**
 * Length units in different systems of measurements.
 */
public class LengthUnits {

    private LengthUnits() { }

    /**
     * International System length units. The base unit is the metre.
     */
    public enum SI implements LengthUnit {

        MILLIMETRE(BigDecimal.ONE.divide(BigDecimal.TEN.pow(3))),
        CENTIMETRE(BigDecimal.ONE.divide(BigDecimal.TEN.pow(2))),
        DECIMETRE(BigDecimal.ONE.divide(BigDecimal.TEN)),
        METRE(BigDecimal.ONE),
        DECAMETRE(BigDecimal.TEN),
        HECTOMETRE(BigDecimal.TEN.pow(2)),
        KILOMETRE(BigDecimal.TEN.pow(3));

        private final BigDecimal metres;

        SI(final BigDecimal metres) {
            this.metres = metres;
        }

        @Override
        public BigDecimal toMetres() {
            return metres;
        }
    }

    /**
     * Imperial and United States customary units. The base unit is the <a href="https://en.wikipedia.org/wiki/Yard">Yard</a>.
     */
    public enum ImperialUS implements LengthUnit {
        THOU(0.0000254),
        INCH(0.0254),
        FOOT(0.3048),
        YARD(0.9144),
        CHAIN(20.1168),
        FURLONG(201.168),
        MILE(1609.344),
        LEAGUE(4828.032);

        private final double metres;

        ImperialUS(final double metres) {
            this.metres = metres;
        }

        @Override
        public BigDecimal toMetres() {
            return BigDecimal.valueOf(metres);
        }
    }
}
