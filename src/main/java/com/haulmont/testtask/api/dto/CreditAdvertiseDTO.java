package com.haulmont.testtask.api.dto;

import com.haulmont.testtask.entity.CreditGraph;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

/**
 * DTO класс CreditAdvertise
 */
@Data
public class CreditAdvertiseDTO {

    private UUID creditAdvertiseId;

    private UUID creditId;

    private Integer creditAmount;

    private BigDecimal interestRate;

    private BigDecimal fullCreditAmount;

    private BigDecimal interestAmount;

    private Integer loanTermMonths;

    private UUID clientId;

    private List<CreditGraph> creditGraphs;

    private List<UUID> creditGraphsId;

}