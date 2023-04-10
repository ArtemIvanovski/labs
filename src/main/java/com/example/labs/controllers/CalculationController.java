package com.example.labs.controllers;

import com.example.labs.async.NumberAsync;
import com.example.labs.cache.Cache;
import com.example.labs.calculations.Calculation;
import com.example.labs.counter.Counter;
import com.example.labs.counter.CounterThread;
import com.example.labs.exceptions.IllegalArgumentsException;
import com.example.labs.models.Numbers;
import com.example.labs.services.NumberService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CalculationController {

    private final Calculation calculation;
    private static final Logger LOGGER = LogManager.getLogger(CalculationController.class);
    private final Cache<List<Double>, List<Double>> cache;
    private final NumberService numberService;
    private final NumberAsync numberAsync;
    @Autowired
    public CalculationController(Calculation calculation, Cache<List<Double>,
            List<Double>> cache, NumberService numberService, NumberAsync numberAsync) {
        this.calculation = calculation;
        this.cache = cache;
        this.numberService = numberService;
        this.numberAsync = numberAsync;
    }

    @GetMapping("/calculation")
    public ResponseEntity<?> calculate(@RequestParam("number1") double number1, @RequestParam("number2") double number2)
            throws IllegalArgumentsException {

        CounterThread counter = new CounterThread();
        counter.start();

        LOGGER.info("GetMapping by address localhost:8080/calculation?number1=...&number2=...");

        Numbers numbers = new Numbers();
        List<Double> params = new ArrayList<>();

        params.add(number1);
        params.add(number2);

        numbers.setNumber1(number1);
        numbers.setNumber2(number2);

        List<Double> results = new ArrayList<>();
        if (cache.isContains(params)) {
            LOGGER.info("get from cache");
            results = cache.get(params);
        } else {
            LOGGER.info("Calculate");
            double resultOfSummary = calculation.sum(number1, number2);
            double resultOfSubtraction = calculation.sub(number1, number2);
            double resultOfMultiplication = calculation.mul(number1, number2);
            double resultOfDivision = calculation.div(number1, number2);

            results.add(resultOfSummary);
            results.add(resultOfSubtraction);
            results.add(resultOfMultiplication);
            results.add(resultOfDivision);

            LOGGER.info("push in cache");
            cache.push(params, results);
        }

        numbers.setSum(results.get(0));
        numbers.setSub(results.get(1));
        numbers.setMul(results.get(2));
        numbers.setDiv(results.get(3));

        numberService.save(numbers);
        return new ResponseEntity<>("Result: " + Counter.getCounter() + ", " + results, HttpStatus.OK);
    }


    @PostMapping("/calculation")
    public ResponseEntity<?> calculationWithBulkOperations(@RequestBody List<List<Double>> listOfNumbers) {

        List<Double> responseListForSum = listOfNumbers.stream().map(calculation::calculateWithBulkParametersForSum).collect(Collectors.toList());
        List<Double> responseListForSub = listOfNumbers.stream().map(calculation::calculateWithBulkParametersForSub).collect(Collectors.toList());
        List<Double> responseListForMul = listOfNumbers.stream().map(calculation::calculateWithBulkParametersForMul).collect(Collectors.toList());
        List<Double> responseListForDiv = listOfNumbers.stream().map(calculation::calculateWithBulkParametersForDiv).collect(Collectors.toList());



        double min = 0;
        double max = 0;
        double sum = 0;

        if(!listOfNumbers.isEmpty()) {
            min = responseListForSum.stream().min(Double::compareTo).get();
            max = responseListForSum.stream().max(Double::compareTo).get();
            sum = responseListForSum.stream().mapToDouble(Double::doubleValue).sum();
        }


        return new ResponseEntity<>("Result: + : " + responseListForSum  +
                "\nResult: - :" + responseListForSub +
                "\nResult: / :" + responseListForDiv +
                "\nResult: * :" + responseListForMul + "\nmin: " + min + "\nmax: " + max + "\nsum: " + sum, HttpStatus.OK);
    }


    @PostMapping("/async")
    public Integer async(@RequestBody Numbers numbers){
        int id = numberAsync.createAsync(numbers);

        numberAsync.computeAsync(id);

        return id;
    }

    @GetMapping("/result/{id}")
    public Numbers result(@PathVariable("id")int id){
        return numberService.findOne(id);
    }


}


