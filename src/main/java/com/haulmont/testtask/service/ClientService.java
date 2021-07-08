package com.haulmont.testtask.service;

import com.haulmont.testtask.entity.Client;

import java.util.List;
import java.util.UUID;

/**
 * Интерфейс для работы с ClientServiceImpl
 */
public interface ClientService {

    /**
     * Создание и запись в БД рандомного Client
     *
     * @return - client записанный в базу
     */
    Client createRandomClient();

    /**
     * Создание и запись в БД екземпляра Client
     *
     * @param client - Экземпляр Client
     * @return - сustomer записанный в базу
     */
    Client create(Client client);

    /**
     * Обновление случайными данными и запись в БД екземпляра Client
     *
     * @param client - экземпляр Client, на который необходимо изменить
     * @return - client обновленный в базе
     */
    Client updateRandomData(Client client);

    /**
     * Обновление и запись в БД экземпляра Client
     *
     * @param id       - id экземпляра Client в базе, который необходимо изменить
     * @param Client - экземпляр Client, на который необходимо изменить
     * @return - client обновленный в базе
     */
    Client update(UUID id, Client Client);

    /**
     * Получение Client из базы
     *
     * @param id - id Client, которое необходимо получить
     * @return - client полученный из базы или новый client в случае отсутствия такового id в БД
     */
    Client getClient(UUID id);

    /**
     * Получение всех Client из базы
     *
     * @return - коллекция list Client
     */
    List<Client> getAllClient();

    /**
     * Удаление Client из базы по id
     *
     * @param id - id client для удаления
     * @return - удаленный client
     */
    Client deleteById(UUID id);

}
