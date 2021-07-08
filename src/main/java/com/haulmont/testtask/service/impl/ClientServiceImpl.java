package com.haulmont.testtask.service.impl;

import com.haulmont.testtask.entity.Client;

import com.haulmont.testtask.repository.ClientRepository;
import com.haulmont.testtask.service.ClientService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * Сервис для работы с clientRepository
 */
@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    /**
     * Создание и запись в БД рандомного Client
     *
     * @return - сustomer записанный в базу
     */
    @Override
    public Client createRandomClient() {
        Client client = new Client();
        client.setFullName(generateRandomWord());
        client.setPhone(getRandomNumber());
        client.setPassportNumber(getRandomNumber());
        client.setEmail(generateRandomWord());
        Client clientCheck = clientRepository.save(client);
        log.debug("createRandomClient() Объект client успешно записан в БД: {} ", client);
        return clientCheck;
    }

    /**
     * Создание и запись в БД екземпляра client
     *
     * @param client - Экземпляр client
     * @return - clientзаписанный в базу
     */
    @Override
    public Client create(Client client) {
        Client clientCheck = clientRepository.save(client);
        log.debug("create() Объект Client успешно записан в БД: {} ", clientCheck);
        return clientCheck;
    }

    /**
     * Обновление случайными данными и запись в БД екземпляра Client
     *
     * @param client - экземпляр Client, на который необходимо изменить
     * @return - сustomer обновленный в базе
     */
    @Override
    public Client updateRandomData(Client client) {
        client.setFullName(client.getFullName() + "+" + generateRandomWord());
        Client clientCheck = clientRepository.save(client);
        log.debug("updateRandomData() Объект Client успешно обновлен в БД: {} ", clientCheck);
        return clientCheck;
    }

    /**
     * Обновление и запись в БД экземпляра Client
     *
     * @param id       - id экземпляра Client в базе, который необходимо изменить
     * @param client - экземпляр Client, на который необходимо изменить
     * @return - сustomer обновленный в базе
     */
    @Override
    public Client update(UUID id, Client client) {
        Client updateClient = clientRepository.getById(id);
        updateClient.setFullName(client.getFullName());
        updateClient.setPhone(client.getPhone());
        updateClient.setEmail(client.getEmail());
        updateClient.setPassportNumber(client.getPassportNumber());
        Client clientCheck = clientRepository.save(updateClient);
        log.debug("updateRandomData() Объект Client успешно обновлен в БД: {} ", clientCheck);
        return clientCheck;
    }

    /**
     * Получение Client из базы
     *
     * @param id - id Client, которое необходимло получить
     * @return - client полученный из базы или новый сustomer в случае отстутствия такового id в БД
     */
    @Override
    public Client getClient(UUID id) {
        Client client = clientRepository.findById(id).orElseThrow(RuntimeException::new);
        log.debug("getClient() Объект Client успешно получен из БД: {}", client);
        return client;
    }

    /**
     * Получение всех Client из базы
     *
     * @return - коллекция list Client
     */
    @Override
    public List<Client> getAllClient() {
        List<Client> clientList = clientRepository.findAll();
        log.debug("getAllClient() Объекты Client успешно получены из БД: {}", clientList);
        return clientList;
    }

    /**
     * Удаление Client из базы по id
     *
     * @param id - id Client для удаления
     * @return - удаленный Client
     */
    @Override
    public Client deleteById(UUID id) {
        Client client = clientRepository.getById(id);
        clientRepository.deleteById(id);
        log.debug("deleteById() Объект Client успешно удален из БД: {}", client);
        return client;
    }

    /**
     * Генерация случайного числа в заданном диапазоне
     *
     * @return - случайное число
     */
    public String getRandomNumber() {
        return Integer.toString(1 + (int) (Math.random() * 10000));
    }

    /**
     * Генерация случайного слова
     *
     * @return - случайное слово
     */
    public String generateRandomWord() {
        Random random = new Random();
        char[] word = new char[random.nextInt(2) + 3];
        for (int j = 0; j < word.length; j++) {
            word[j] = (char) ('a' + random.nextInt(26));
        }
        return new String(word);
    }

}
