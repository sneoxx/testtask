package com.haulmont.testtask.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
    private UUID clientId;

    private String fullName;

    private String phone;

    private String email;

    private String passportNumber;

}
