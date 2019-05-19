package com.reneuby.validator.properties;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

public class MoneyRating {
    public static Set<BigDecimal> values = new TreeSet<>(
            Arrays.asList(
                    new BigDecimal(100),
                    new BigDecimal(200),
                    new BigDecimal(500)
            )
    );
}
