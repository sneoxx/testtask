package com.haulmont.testtask.service.impl;

import com.haulmont.testtask.entity.CreditAdvertise;
import com.haulmont.testtask.entity.CreditGraph;
import com.haulmont.testtask.repository.CreditAdvertiseRepository;
import com.haulmont.testtask.service.CreditAdvertiseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Сервис для работы с CreditAdvertiseRepository
 */
@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class CreditAdvertiseServiceImpl implements CreditAdvertiseService {

    private final CreditAdvertiseRepository creditAdvertiseRepository;

    /**
     * Создание и запись в БД екземпляра CreditAdvertise
     *
     * @param creditAdvertise - Экземпляр CreditAdvertise
     * @return - CreditAdvertise записанный в базу
     */
    @Override
    public CreditAdvertise create(CreditAdvertise creditAdvertise) {
        creditAdvertise.setCreditGraphs(getCreditGraphList(creditAdvertise));
        CreditAdvertise creditAdvertiseCheck = creditAdvertiseRepository.save(creditAdvertise);
        log.info("create() Объект CreditAdvertise успешно записан в БД: {} ", creditAdvertiseCheck);
        return creditAdvertiseCheck;
    }

    /**
     * Получение всех CreditAdvertise из базы
     *
     * @return - коллекция list CreditAdvertise
     */
    @Override
    public List<CreditAdvertise> getAllCreditAdvertise() {
        List<CreditAdvertise> creditAdvertiseList = creditAdvertiseRepository.findAll();
        log.debug("getAllCreditAdvertise() Объекты CreditAdvertise успешно получены из БД: {}", creditAdvertiseList);
        return creditAdvertiseList;
    }

    /**
     * Удаление CreditAdvertise из базы по id
     *
     * @param id - id CreditAdvertise для удаления
     * @return - удаленный CreditAdvertise
     */
    @Override
    public CreditAdvertise deleteById(UUID id) {
        CreditAdvertise creditAdvertise = creditAdvertiseRepository.getById(id);
        creditAdvertiseRepository.deleteById(id);
        log.debug("deleteById() Объект CreditAdvertise успешно удален из БД: {}", creditAdvertise);
        return creditAdvertise;
    }


    /**
     * Получить график платежей кредитного предложения
     *
     * @param creditAdvertise - экземпляр creditAdvertise
     * @return - creditGraphList
     */
    public List<CreditGraph> getCreditGraphList(CreditAdvertise creditAdvertise) {
        BigDecimal interestAmount = (creditAdvertise.getCredit().getInterestRate().multiply(creditAdvertise.getCreditAmount())
                .divide(new BigDecimal(100), 2)).divide(new BigDecimal(12), 2)
                .multiply((new BigDecimal(creditAdvertise.getLoanTermMonths())));
        BigDecimal fullCreditAmount = creditAdvertise.getCreditAmount().add(interestAmount);
        Integer loanTermMonths = creditAdvertise.getLoanTermMonths();
        List<CreditGraph> creditGraphList = new ArrayList<>();
        int paymentDate = 0;
        for (int i = 0; i < loanTermMonths; i++) {
            CreditGraph creditGraph = new CreditGraph();
            creditGraph.setCreditAdvertise(creditAdvertise);
            creditGraph.setAmountPayment(fullCreditAmount.divide(new BigDecimal(loanTermMonths), 2));
            creditGraph.setBodyPayment(creditAdvertise.getCreditAmount().divide(new BigDecimal(loanTermMonths), 2));
            creditGraph.setInterestPayment(interestAmount.divide(new BigDecimal(loanTermMonths), 2));
            creditGraph.setPaymentDate(LocalDate.now().plusMonths(paymentDate));
            paymentDate++;
            creditGraphList.add(creditGraph);
        }
        return creditGraphList;
    }

}