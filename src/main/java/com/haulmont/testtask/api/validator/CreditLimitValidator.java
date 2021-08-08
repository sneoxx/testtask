package com.haulmont.testtask.api.validator;

import com.haulmont.testtask.api.dto.CreditAdvertiseDTO;
import com.haulmont.testtask.service.CreditService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


@RequiredArgsConstructor
@Component
public class CreditLimitValidator implements Validator {

    private final CreditService creditService;

    @Override
    public boolean supports(@NonNull Class<?> clazz) {
        return CreditAdvertiseDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(@NonNull Object target, @NonNull Errors errors) {
        CreditAdvertiseDTO creditAdvertiseForm = (CreditAdvertiseDTO) target;

        if (creditAdvertiseForm.getLoanTermMonths() == null) {
            errors.rejectValue("loanTermMonths", "Cannot be null", "Cannot be null");
            errors.reject("loanTermMonths", "Cannot be null");
        }

        if (creditAdvertiseForm.getCreditAmount() == null) {
            errors.rejectValue("creditAmount", "Cannot be null", "Cannot be null");
            errors.reject("creditAmount", "Cannot be null");
        }

        if (creditAdvertiseForm.getCreditAmount() != null && creditAdvertiseForm.getCreditAmount() < 1) {
            errors.rejectValue("creditAmount", "Cannot be 0 or negative", "Cannot be 0 or negative");
            errors.reject("creditAmount", "Cannot be 0 or negative");
        }

        if (creditAdvertiseForm.getCreditAmount() != null && (creditService.getCredit(creditAdvertiseForm.getCreditId()).getCreditLimit().compareTo(creditAdvertiseForm.getCreditAmount())) < 0) {
            errors.rejectValue("creditAmount", "Exceeded Credit Limit", "Exceeded Credit Limit");
            errors.reject("creditAmount", "Exceeded Credit Limit");
        }

        if (creditAdvertiseForm.getCreditAmount() != null && creditAdvertiseForm.getLoanTermMonths() != null && creditAdvertiseForm.getLoanTermMonths() < 1) {
            errors.rejectValue("loanTermMonths", "Cannot be 0 or negative", "Cannot be 0 or negative");
            errors.reject("loanTermMonths", "Cannot be 0 or negative");
        }

        if (creditAdvertiseForm.getCreditAmount() != null && creditAdvertiseForm.getLoanTermMonths() != null && creditAdvertiseForm.getLoanTermMonths() > 100) {
            errors.rejectValue("loanTermMonths", "Cannot be more than 100", "Cannot be more than 100");
            errors.reject("loanTermMonths", "Cannot be more than 100");
        }

    }
}