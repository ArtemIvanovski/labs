package com.example.labs.services;

import com.example.labs.models.Numbers;
import com.example.labs.repositories.NumberRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NumberService {

    private final NumberRepository numberRepository;


    public NumberService(NumberRepository numberRepository) {
        this.numberRepository = numberRepository;
    }


    public void save(Numbers numbers) {
        numberRepository.save(numbers);
    }


    public Numbers findOne(int id) {
        Optional<Numbers> numbers = numberRepository.findById(id);

        return numbers.orElse(null);
    }


}
