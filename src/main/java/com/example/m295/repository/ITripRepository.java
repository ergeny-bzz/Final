package com.example.m295.repository;

import com.example.m295.model.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


    @Repository
    public interface ITripRepository extends JpaRepository<Trip, Integer> {
    }

