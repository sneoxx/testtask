package com.haulmont.testtask.exception;

/**
 * Класс исключения для процерки введенного CreditLimit
 */
public class CreditLimitException extends RuntimeException {

    public CreditLimitException(String message) {
        super(message);
    }

}