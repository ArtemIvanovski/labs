package com.example.labs.async;

import com.example.labs.calculations.Calculation;
import com.example.labs.models.Numbers;
import com.example.labs.services.NumberService;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
public class NumberAsync {

    private final NumberService numberService;
    private final Calculation calculation;

    public NumberAsync(NumberService numberService, Calculation calculation) {
        this.numberService = numberService;
        this.calculation = calculation;
    }

    public Integer createAsync(Numbers numbers) {
        Numbers numbers1 = new Numbers();
        numbers1.setNumber1(numbers.getNumber1());
        numbers1.setNumber2(numbers.getNumber2());

        numberService.save(numbers1);

        return numbers1.getId();
    }

    public void computeAsync(int id) {
        CompletableFuture.supplyAsync(() -> {
            try {
                Numbers result = numberService.findOne(id);

                Thread.sleep(15000);
                result.setSum(calculation.sum(result.getNumber1(), result.getNumber2()));
                result.setSub(calculation.sub(result.getNumber1(), result.getNumber2()));
                result.setMul(calculation.mul(result.getNumber1(), result.getNumber2()));
                result.setDiv(calculation.div(result.getNumber1(), result.getNumber2()));

                numberService.save(result);

                return result.getId();

            } catch (InterruptedException e) {
                throw new RuntimeException();
            }
        });
    }

}
