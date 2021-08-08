package com.haulmont.testtask.converter;

import com.haulmont.testtask.api.dto.CreditDTO;
import com.haulmont.testtask.entity.Credit;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Конвертация из Credit в CreditDTO
 */
@Component
public class CreditDTOFromCreditConverter implements Converter<Credit, CreditDTO> {

    @Override
    public CreditDTO convert(Credit credit) {
        CreditDTO creditDTO = new CreditDTO();
        creditDTO.setCreditId(credit.getCreditId());
        creditDTO.setCreditName(credit.getCreditName());
        creditDTO.setCreditLimit(credit.getCreditLimit().toString());
        creditDTO.setInterestRate(credit.getInterestRate().toString());
        creditDTO.setCreditAdvertise(credit.getCreditAdvertise());
        return creditDTO;
    }

}