package com.haulmont.testtask.service.impl;

import com.haulmont.testtask.entity.CreditGraph;
import com.haulmont.testtask.repository.CreditGraphRepository;
import com.haulmont.testtask.service.CreditGraphService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * Сервис для работы с CreditGraphRepository
 */
@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class CreditGraphServiceImpl implements CreditGraphService {

    private final CreditGraphRepository creditGraphRepository;

    /**
     * Получение всех CreditGraph из базы с сортировкой по дате платежа
     *
     * @return - коллекция list CreditGraph
     */
    @Override
    public List<CreditGraph> getAllCreditGraph() {
        List<CreditGraph> creditGraphList = creditGraphRepository.findAll(Sort.by(Sort.Direction.ASC, "paymentDate"));
        log.debug("getAllCreditGraph() Объекты CreditGraph успешно получены из БД: {}", creditGraphList);
        return creditGraphList;
    }

    /**
     * Удаление CreditGraph из базы по id
     *
     * @param id - id CreditGraph для удаления
     * @return - удаленный CreditGraph
     */
    @Override
    public CreditGraph deleteById(UUID id) {
        CreditGraph creditGraph = creditGraphRepository.getById(id);
        creditGraphRepository.deleteById(id);
        log.debug("deleteById() Объект CreditGraph успешно удален из БД: {}", creditGraph);
        return creditGraph;
    }

}
