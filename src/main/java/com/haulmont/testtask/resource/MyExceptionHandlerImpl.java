package com.haulmont.testtask.resource;

import com.haulmont.testtask.exception.CreditLimitException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Обработчик ошибок CreditLimit
 */
@Slf4j
public class MyExceptionHandlerImpl {

    @ExceptionHandler(CreditLimitException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Very very bad request")
    public String handleException(CreditLimitException e) {
        log.error("handleException() - CreditLimitException...", e);
        return e.getMessage();
    }

}