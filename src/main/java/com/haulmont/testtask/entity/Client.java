package com.haulmont.testtask.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

/**
 * Entity класс для работы с таблицей client базы данных
 */
@Data
@NoArgsConstructor
@Entity
@Table
public class Client {

    @Id
    @GeneratedValue
    @Column(name = "client_id")
    private UUID clientId;

    private String fullName;

    private String phone;

    private String email;

    private String passportNumber;

    @ToString.Exclude
    @ManyToMany(mappedBy = "clientList", fetch = FetchType.EAGER)
    private List<CreditAdvertise> creditAdvertiseList;

}