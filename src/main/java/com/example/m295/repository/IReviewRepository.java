package com.example.m295.repository;

import com.example.m295.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import jakarta.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface IReviewRepository extends JpaRepository<Review, Integer> {

    boolean existsByRecommend(boolean recommend);

    List<Review> findAllByRecommend(boolean recommend);

    boolean existsByReview(String review);

    List<Review> findAllByReview(String review);

    @Modifying
    @Transactional
    @Query("DELETE FROM Review r WHERE r.createdAt = :date")
    void deleteAllByCreatedAt(LocalDateTime date);

}
