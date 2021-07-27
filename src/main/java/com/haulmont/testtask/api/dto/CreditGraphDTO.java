package com.haulmont.testtask.api.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

/**
 * DTO класс CreditGraph
 */
@Data
public class CreditGraphDTO {

    private UUID creditGraphId;

    private LocalDate paymentDate;

    private BigDecimal amountPayment;

    private BigDecimal bodyPayment;

    private BigDecimal interestPayment;

    private UUID creditAdvertise;

}