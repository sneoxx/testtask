package com.haulmont.testtask.converter;

import com.haulmont.testtask.api.dto.CreditGraphDTO;
import com.haulmont.testtask.entity.CreditGraph;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Конвертация из CreditGraph в CreditGraphDTO
 */
@Component
public class CreditGraphDTOFromCreditGraphConverter implements Converter<CreditGraph, CreditGraphDTO> {

    @Override
    public CreditGraphDTO convert(CreditGraph creditGraph) {
        CreditGraphDTO creditGraphDTO = new CreditGraphDTO();
        creditGraphDTO.setCreditGraphId(creditGraph.getCreditGraphId());
        creditGraphDTO.setCreditAdvertise(creditGraph.getCreditAdvertise().getCreditAdvertiseId());
        creditGraphDTO.setPaymentDate(creditGraph.getPaymentDate());
        creditGraphDTO.setAmountPayment(creditGraph.getAmountPayment());
        creditGraphDTO.setBodyPayment(creditGraph.getBodyPayment());
        creditGraphDTO.setInterestPayment(creditGraph.getInterestPayment());
        return creditGraphDTO;
    }

}