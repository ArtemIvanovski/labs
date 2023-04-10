package com.example.labs.calculations;

import com.example.labs.controllers.CalculationController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Calculation {

    private static final Logger LOGGER = LogManager.getLogger(CalculationController.class);

    public double sum(double a, double b) {
        return a + b;
    }

    public double sub(double a, double b) {

        return a - b;
    }

    public double mul(double a, double b) {

        return a * b;
    }

    public double div(double a, double b) {

        if (b == 0) {
            LOGGER.error("Arithmetic exception");
            throw new ArithmeticException("Second argument is zero");
        }
        return a / b;
    }

    public double calculateWithBulkParametersForSum(List<Double> listOfPairs) {
        return listOfPairs.get(0) + listOfPairs.get(1);
    }

    public double calculateWithBulkParametersForDiv(List<Double> listOfPairs) {
        return listOfPairs.get(0) / listOfPairs.get(1);
    }

    public double calculateWithBulkParametersForSub(List<Double> listOfPairs) {
        return listOfPairs.get(0) - listOfPairs.get(1);
    }

    public double calculateWithBulkParametersForMul(List<Double> listOfPairs) {
        return listOfPairs.get(0) * listOfPairs.get(1);
    }

}
