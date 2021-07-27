package com.haulmont.testtask.api.dto;

import com.haulmont.testtask.entity.CreditAdvertise;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.UUID;

/**
 * DTO класс Credit
 */
@Data
public class CreditDTO {

    private UUID creditId;

    @NotBlank
    @Pattern(regexp = "[0-9]+", message = "Invalid Credit Limit")
    @Min(1)
    @Size(max = 12)
    private String creditLimit;

    @Pattern(regexp = "[0-9.]+", message = "Invalid Interest Rate")
    @NotBlank
    private String interestRate;

    private List<CreditAdvertise> creditAdvertise;

}