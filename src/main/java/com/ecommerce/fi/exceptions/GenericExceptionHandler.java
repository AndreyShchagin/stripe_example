package com.ecommerce.fi.exceptions;

import com.stripe.exception.StripeException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author Andrey Shchagin on 06/02/19
 */
@ControllerAdvice
public class GenericExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value
            = {StripeException.class})
    protected ResponseEntity<Object> handleTransferException(
            RuntimeException ex, WebRequest request) {
        return handleExceptionInternal(ex, "Transaction was not processed", new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @ExceptionHandler(value
            = {GenericFiException.class})
    protected ResponseEntity<Object> handleGenericException(
            RuntimeException ex, WebRequest request) {
        return handleExceptionInternal(ex, "Not accepted", new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }
}