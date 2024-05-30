package com.example.mvc_webapp.repository;

import com.example.mvc_webapp.model.Modul;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


    @Repository
    public interface IModuleRepository extends JpaRepository<Modul, Integer> {
    }

