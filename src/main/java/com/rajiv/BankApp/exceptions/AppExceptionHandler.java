package com.rajiv.BankApp.exceptions;

import org.modelmapper.spi.ErrorMessage;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler(value = {AccountServiceException.class})
    public String handleAccountServiceException(AccountServiceException ex, HttpServletRequest request)
    {
        ErrorMessage errorMessage = new ErrorMessage(ex.getMessage());
        request.setAttribute("message", errorMessage);

        return "forward:/error";
    }

    @ExceptionHandler(value = {Exception.class})
    public String handleOtherExceptions(Exception ex, HttpServletRequest request)
    {
        ErrorMessage errorMessage = new ErrorMessage(ex.getMessage());
        request.setAttribute("message", errorMessage);

        return "forward:/error";
    }
}
