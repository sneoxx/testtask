package com.haulmont.testtask.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

/**
 * Entity класс для работы с таблицей credit базы данных
 */
@Data
@NoArgsConstructor
@Entity
@Table
public class CreditGraph {

    @Id
    @GeneratedValue
    @Column(name = "credit_graph_id")
    private UUID creditGraphId;

    private LocalDate paymentDate;

    private BigDecimal amountPayment;

    private BigDecimal bodyPayment;

    private BigDecimal interestPayment;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "credit_advertise_id")
    private CreditAdvertise creditAdvertise;

}