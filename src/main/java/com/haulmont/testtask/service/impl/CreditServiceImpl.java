package com.haulmont.testtask.service.impl;

import com.haulmont.testtask.entity.Credit;
import com.haulmont.testtask.repository.CreditRepository;
import com.haulmont.testtask.service.CreditService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * Сервис для работы с CreditRepository
 */
@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class CreditServiceImpl implements CreditService {

    private final CreditRepository creditRepository;

    /**
     * Создание и запись в БД екземпляра Credit
     *
     * @param credit - Экземпляр Credit
     * @return - Credit записанный в базу
     */
    @Override
    public Credit create(Credit credit) {
        Credit creditCheck = creditRepository.save(credit);
        log.debug("create() Объект Credit успешно записан в БД: {} ", creditCheck);
        return creditCheck;
    }

    /**
     * Обновление и запись в БД экземпляра Credit
     *
     * @param credit - экземпляр Credit, на который необходимо изменить
     * @return - Credit обновленный в базе
     */
    @Override
    public Credit update(Credit credit) {
        Credit updateCredit = creditRepository.getById(credit.getCreditId());
        updateCredit.setCreditLimit(credit.getCreditLimit());
        updateCredit.setInterestRate(credit.getInterestRate());
        Credit creditCheck = creditRepository.save(updateCredit);
        log.debug("updateRandomData() Объект Credit успешно обновлен в БД: {} ", creditCheck);
        return creditCheck;
    }

    /**
     * Получение Credit из базы
     *
     * @param id - id Credit, которое необходимо получить
     * @return - Credit полученный из базы или новый сustomer в случае отстутствия такового id в БД
     */
    @Override
    public Credit getCredit(UUID id) {
        Credit credit = creditRepository.findById(id).orElseThrow(RuntimeException::new);
        log.debug("getCredit() Объект Credit успешно получен из БД: {}", credit);
        return credit;
    }

    /**
     * Получение всех Credit из базы
     *
     * @return - коллекция list Credit
     */
    @Override
    public List<Credit> getAllCredit() {
        List<Credit> creditList = creditRepository.findAll();
        log.debug("getAllCredit() Объекты Credit успешно получены из БД: {}", creditList);
        return creditList;
    }

    /**
     * Удаление Credit из базы по id
     *
     * @param id - id Credit для удаления
     * @return - удаленный Credit
     */
    @Override
    public Credit deleteById(UUID id) {
        Credit credit = creditRepository.getById(id);
        creditRepository.deleteById(id);
        log.debug("deleteById() Объект Credit успешно удален из БД: {}", credit);
        return credit;
    }
}