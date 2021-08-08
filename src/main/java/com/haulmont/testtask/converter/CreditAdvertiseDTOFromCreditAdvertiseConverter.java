package com.haulmont.testtask.converter;

import com.haulmont.testtask.api.dto.CreditAdvertiseDTO;
import com.haulmont.testtask.entity.Client;
import com.haulmont.testtask.entity.CreditAdvertise;
import com.haulmont.testtask.entity.CreditGraph;
import com.haulmont.testtask.service.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Конвертация из Credit в CreditDTO
 */
@AllArgsConstructor
@Component
public class CreditAdvertiseDTOFromCreditAdvertiseConverter implements Converter<CreditAdvertise, CreditAdvertiseDTO> {

    @Override
    public CreditAdvertiseDTO convert(CreditAdvertise creditAdvertise) {
        CreditAdvertiseDTO creditAdvertiseDTO = new CreditAdvertiseDTO();
        creditAdvertiseDTO.setCreditAdvertiseId(creditAdvertise.getCreditAdvertiseId());
        creditAdvertiseDTO.setCreditAmount(creditAdvertise.getCreditAmount().intValue());
        creditAdvertiseDTO.setLoanTermMonths(creditAdvertise.getLoanTermMonths());
        creditAdvertiseDTO.setCreditId(creditAdvertise.getCredit().getCreditId());
        creditAdvertiseDTO.setCreditName(creditAdvertise.getCredit().getCreditName());

        for (Client foundClient : creditAdvertise.getClientList()) {
            UUID foundClientId = foundClient.getClientId();
            creditAdvertiseDTO.setClientId(foundClientId);
            creditAdvertiseDTO.setClientName(foundClient.getFullName());
        }
        List<CreditGraph> creditGraphList = new ArrayList<>();
        for (CreditGraph creditGraphs : creditAdvertise.getCreditGraphs()) {
            CreditGraph creditGraph = new CreditGraph();
            UUID foundCreditGraphs = creditGraphs.getCreditGraphId();
            creditGraph.setCreditGraphId(foundCreditGraphs);
            creditGraphList.add(creditGraph);
        }
        creditAdvertiseDTO.setCreditGraphs(creditGraphList);
        List<UUID> creditGraphListId = new ArrayList<>();
        for (CreditGraph creditGraphs : creditAdvertise.getCreditGraphs()) {
            UUID foundCreditGraphsId = creditGraphs.getCreditGraphId();
            creditGraphListId .add(foundCreditGraphsId);
        }
        creditAdvertiseDTO.setCreditGraphsId(creditGraphListId);
        return creditAdvertiseDTO;
    }

}