package com.example.m295.repository;

import com.example.m295.model.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import jakarta.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ITripRepository extends JpaRepository<Trip, Integer> {



}
