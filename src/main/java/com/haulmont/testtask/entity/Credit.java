package com.haulmont.testtask.entity;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

/**
 * Entity класс для работы с таблицей credit базы данных
 */
@Data
@NoArgsConstructor
@Entity
@Table
public class Credit {

    @Id
    @GeneratedValue
    @Column(name = "credit_id")
    private UUID creditId;

    private String creditName;

    private Integer creditLimit;

    private BigDecimal interestRate;

    @ToString.Exclude
    @OneToMany(mappedBy = "credit", fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private List<CreditAdvertise> creditAdvertise;

}