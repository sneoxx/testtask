package com.haulmont.testtask.repository;

import com.haulmont.testtask.entity.Credit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CreditRepository extends JpaRepository<Credit, UUID> {
}