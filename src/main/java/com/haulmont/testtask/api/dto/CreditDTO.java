package com.haulmont.testtask.api.dto;

import com.haulmont.testtask.entity.CreditAdvertise;
import lombok.Data;

import javax.validation.constraints.*;
import java.util.List;
import java.util.UUID;

/**
 * DTO класс Credit
 */
@Data
public class CreditDTO {

    private UUID creditId;

    @NotBlank
    @Size(min = 3, max = 50)
    private String creditName;

    @NotBlank
    @Pattern(regexp = "[0-9]+", message = "Invalid Credit Limit")
    @Min(1)
    @Size(max = 12)
    private String creditLimit;

    @Pattern(regexp = "[0-9.]+", message = "Invalid Interest Rate")
    @Min(1)
    @Max(99)
    @NotBlank
    private String interestRate;

    private List<CreditAdvertise> creditAdvertise;

}