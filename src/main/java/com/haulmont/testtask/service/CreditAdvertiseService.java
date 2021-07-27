package com.haulmont.testtask.service;

import com.haulmont.testtask.entity.CreditAdvertise;

import java.util.List;
import java.util.UUID;

/**
 * Интерфейс для работы с CreditAdvertiseServiceImpl
 */
public interface CreditAdvertiseService {

    /**
     * Создание и запись в БД екземпляра CreditAdvertise
     *
     * @param creditAdvertise - Экземпляр CreditAdvertise
     * @return - CreditAdvertise записанный в базу
     */
    CreditAdvertise create(CreditAdvertise creditAdvertise);

    /**
     * Получение всех CreditAdvertise из базы
     *
     * @return - коллекция list CreditAdvertise
     */
    List<CreditAdvertise> getAllCreditAdvertise();

    /**
     * Удаление CreditAdvertise из базы по id
     *
     * @param id - id CreditAdvertise для удаления
     * @return - удаленный CreditAdvertise
     */
    CreditAdvertise deleteById(UUID id);

}