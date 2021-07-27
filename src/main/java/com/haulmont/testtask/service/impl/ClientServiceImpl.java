package com.haulmont.testtask.service.impl;

import com.haulmont.testtask.entity.Client;
import com.haulmont.testtask.repository.ClientRepository;
import com.haulmont.testtask.service.ClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
     * Создание и запись в БД екземпляра client
     *
     * @param client - Экземпляр client
     * @return - client записанный в базу
     */
    @Override
    public Client create(Client client) {
        Client clientCheck = clientRepository.save(client);
        log.debug("create() Объект Client успешно записан в БД: {} ", clientCheck);
        return clientCheck;
    }

    /**
     * Обновление и запись в БД экземпляра Client
     *
     * @param client - экземпляр Client, на который необходимо изменить
     * @return - client обновленный в базе
     */
    @Override
    public Client update(Client client) {
        Client updateClient = clientRepository.getById(client.getClientId());
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
     * @return - client полученный из базы или новый client в случае отстутствия такового id в БД
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

}