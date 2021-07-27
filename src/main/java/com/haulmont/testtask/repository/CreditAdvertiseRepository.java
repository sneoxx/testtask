package com.haulmont.testtask.repository;

import com.haulmont.testtask.entity.CreditAdvertise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CreditAdvertiseRepository extends JpaRepository<CreditAdvertise, UUID> {
}