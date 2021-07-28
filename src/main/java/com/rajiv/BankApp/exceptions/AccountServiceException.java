package com.rajiv.BankApp.exceptions;

public class AccountServiceException extends RuntimeException {

    private static final long serialVersionUID = 6858066737486125966L;

    public AccountServiceException(String message)
    {
        super(message);
    }
}
