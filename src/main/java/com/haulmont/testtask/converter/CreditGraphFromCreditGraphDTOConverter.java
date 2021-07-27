package com.haulmont.testtask.converter;

import com.haulmont.testtask.api.dto.CreditGraphDTO;
import com.haulmont.testtask.entity.CreditAdvertise;
import com.haulmont.testtask.entity.CreditGraph;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Конвертация из CreditGraphDTO в CreditGraph
 */
@Component
public class CreditGraphFromCreditGraphDTOConverter implements Converter<CreditGraphDTO, CreditGraph> {

    @Override
    public CreditGraph convert(CreditGraphDTO creditGraphDTO) {
        CreditGraph creditGraph = new CreditGraph();
        creditGraph.setCreditGraphId(creditGraphDTO.getCreditGraphId());
        CreditAdvertise creditAdvertise = new CreditAdvertise();
        creditAdvertise.setCreditAdvertiseId(creditGraphDTO.getCreditAdvertise());
        creditGraph.setCreditAdvertise(creditAdvertise);
        creditGraph.setPaymentDate(creditGraphDTO.getPaymentDate());
        creditGraph.setAmountPayment(creditGraphDTO.getAmountPayment());
        creditGraph.setBodyPayment(creditGraphDTO.getBodyPayment());
        creditGraph.setInterestPayment(creditGraphDTO.getInterestPayment());
        return creditGraph;
    }

}