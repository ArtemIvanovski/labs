package com.example.labs.repositories;

import com.example.labs.models.Numbers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NumberRepository extends JpaRepository<Numbers,Integer> {}
