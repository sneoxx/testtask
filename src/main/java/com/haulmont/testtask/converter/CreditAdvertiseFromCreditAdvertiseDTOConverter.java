package com.haulmont.testtask.converter;

import com.haulmont.testtask.api.dto.CreditAdvertiseDTO;
import com.haulmont.testtask.entity.Client;
import com.haulmont.testtask.entity.Credit;
import com.haulmont.testtask.entity.CreditAdvertise;
import com.haulmont.testtask.service.ClientService;
import com.haulmont.testtask.service.CreditService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Конвертация из CreditAdvertiseDTO в CreditAdvertise
 */
@RequiredArgsConstructor
@Component
public class CreditAdvertiseFromCreditAdvertiseDTOConverter implements Converter<CreditAdvertiseDTO, CreditAdvertise> {

    private final ClientService clientService;

    private final CreditService creditService;

    @Override
    public CreditAdvertise convert(CreditAdvertiseDTO creditAdvertiseDTO) {
                CreditAdvertise creditAdvertise = new CreditAdvertise();
        creditAdvertise.setCreditAmount(new BigDecimal(creditAdvertiseDTO.getCreditAmount()));
        creditAdvertise.setLoanTermMonths(creditAdvertiseDTO.getLoanTermMonths());
        creditAdvertise.setCreditGraphs(creditAdvertiseDTO.getCreditGraphs());
        Credit credit = creditService.getCredit(creditAdvertiseDTO.getCreditId());
        creditAdvertise.setCredit(credit);
        List<Client> clientList = new ArrayList<>();
        clientList.add(clientService.getClient(creditAdvertiseDTO.getClientId()));
        creditAdvertise.setClientList(clientList);
        return creditAdvertise;
    }

}