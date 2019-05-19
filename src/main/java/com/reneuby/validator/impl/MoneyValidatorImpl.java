package com.reneuby.validator.impl;

import com.reneuby.validator.MoneyValidator;
import com.reneuby.validator.properties.MoneyRating;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class MoneyValidatorImpl implements MoneyValidator {

    @Override
    public boolean validateMoneyDenomination(String money) {
        for (BigDecimal b : MoneyRating.values) {
            if (Double.valueOf(money) % b.doubleValue() == 0)
                return true;
        }
        return false;
    }
}
