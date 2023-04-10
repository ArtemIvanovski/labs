package com.example.labs.controllers;

import com.example.labs.calculations.Calculation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CalculationTest {

    private final double EPS = 1e-2;

    @Test
    public void validationShouldThrowException() {
        Calculation calculation = new Calculation();
        Assertions.assertThrows(ArithmeticException.class, () -> {
            calculation.div(7, 0);
        });
    }


    @Test
    public void resultOfDivision() {
        Calculation calculation = new Calculation();
        Assertions.assertEquals(0, calculation.div(0, 10));
    }

    @Test
    public void resultOfSummary() {
        Calculation calculation = new Calculation();
        Assertions.assertEquals(10, calculation.div(5, 5));
    }

    @Test
    public void resultOfSubtraction() {
        Calculation calculation = new Calculation();
        Assertions.assertEquals(5, calculation.sub(15, 10));
    }

    @Test
    public void resultOfMultiplication() {
        Calculation calculation = new Calculation();
        Assertions.assertEquals(10, calculation.mul(1, 10));
    }


}
