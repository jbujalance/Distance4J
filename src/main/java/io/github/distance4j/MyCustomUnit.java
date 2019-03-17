package io.github.distance4j;

import java.math.BigDecimal;

public class MyCustomUnit implements LengthUnit {

    @Override
    public BigDecimal toMetres() {
        return BigDecimal.valueOf(2);
    }

}
