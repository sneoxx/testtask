package com.haulmont.testtask.service;

import com.haulmont.testtask.entity.CreditGraph;

import java.util.List;
import java.util.UUID;

/**
 * Интерфейс для работы с CreditGraphServiceImpl
 */
public interface CreditGraphService {

    /**
     * Получение всех CreditGraph из базы
     *
     * @return - коллекция list CreditGraph
     */
    List<CreditGraph> getAllCreditGraph();

    /**
     * Удаление CreditGraph из базы по id
     *
     * @param id - id CreditGraph для удаления
     * @return - удаленный CreditGraph
     */
    CreditGraph deleteById(UUID id);

}