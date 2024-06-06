package com.example.m295.repository;


import com.example.m295.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

    @Repository
    public interface IReviewRepository extends JpaRepository<Review, Integer> {
    }

