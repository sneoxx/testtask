package com.haulmont.testtask.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

/**
 * DTO класс Client
 */
@Data
public class ClientDTO {

    private UUID clientId;

    @NotBlank
    private String fullName;

    private String phone;

    private String email;

    private String passportNumber;
}
