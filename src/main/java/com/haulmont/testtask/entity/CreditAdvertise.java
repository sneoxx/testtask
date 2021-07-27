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
public class CreditAdvertise {

    @Id
    @GeneratedValue
    @Column(name = "credit_advertise_id")
    private UUID creditAdvertiseId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "credit_id") //создать поле
    private Credit credit;

    private BigDecimal creditAmount;

    private Integer loanTermMonths;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(
            name = "bank",
            joinColumns = @JoinColumn(name = "credit_advertise_id", referencedColumnName = "credit_advertise_id"),
            inverseJoinColumns = @JoinColumn(name = "client_id", referencedColumnName = "client_id")
    )
    private List<Client> clientList;

    @ToString.Exclude
    @OneToMany(mappedBy = "creditAdvertise", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<CreditGraph> creditGraphs;

}