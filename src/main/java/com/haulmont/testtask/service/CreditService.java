package com.haulmont.testtask.service;

import com.haulmont.testtask.entity.Credit;

import java.util.List;
import java.util.UUID;

/**
 * Интерфейс для работы с CreditServiceImpl
 */
public interface CreditService {

    /**
     * Создание и запись в БД екземпляра Credit
     *
     * @param credit - Экземпляр Credit
     * @return - credit записанный в базу
     */
    Credit create(Credit credit);

    /**
     * Обновление и запись в БД экземпляра Credit
     *
     * @param credit - экземпляр Credit, на который необходимо изменить
     * @return - Credit обновленный в базе
     */
    Credit update(Credit credit);

    /**
     * Получение Credit из базы
     *
     * @param id - id Credit, которое необходимо получить
     * @return - Credit полученный из базы или новый Credit в случае отсутствия такового id в БД
     */
    Credit getCredit(UUID id);

    /**
     * Получение всех Credit из базы
     *
     * @return - коллекция list Credit
     */
    List<Credit> getAllCredit();

    /**
     * Удаление Credit из базы по id
     *
     * @param id - id Credit для удаления
     * @return - удаленный Credit
     */
    Credit deleteById(UUID id);

}