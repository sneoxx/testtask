package com.haulmont.testtask.api.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.UUID;

/**
 * DTO класс Client
 */
@Data
public class ClientDTO {

    private UUID clientId;

    @NotBlank
    @Size(max = 50)
    @Pattern(regexp = "[а-яА-Я]+", message = "Invalid Full Name")
    private String fullName;

    @Pattern(regexp = "[0-9+]+", message = "Invalid Phone")
    @Size(min = 6, max = 12)
    private String phone;

    @Email
    @Size(min = 5, max = 50)
    private String email;

    @NotBlank
    @Size(min = 10, max = 10)
    private String passportNumber;

}