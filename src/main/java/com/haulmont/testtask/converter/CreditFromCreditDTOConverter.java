package com.haulmont.testtask.converter;

import com.haulmont.testtask.api.dto.CreditDTO;
import com.haulmont.testtask.entity.Credit;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * Конвертация из CreditDTO в Credit
 */
@Component
public class CreditFromCreditDTOConverter implements Converter<CreditDTO, Credit> {

    @Override
    public Credit convert(CreditDTO creditDTO) {
        Credit credit = new Credit();
        credit.setCreditId(creditDTO.getCreditId());
        credit.setCreditLimit(Integer.valueOf(creditDTO.getCreditLimit()));
        credit.setInterestRate(new BigDecimal(creditDTO.getInterestRate()));
        credit.setCreditAdvertise(creditDTO.getCreditAdvertise());
        return credit;
    }

}