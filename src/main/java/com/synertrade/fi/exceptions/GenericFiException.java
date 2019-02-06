package com.synertrade.fi.exceptions;

/**
 * Custom FI module exception
 *
 * @author Andrey Shchagin on 03.05.17.
 *
 */
public class GenericFiException extends Exception{
    public GenericFiException(String message){
        super(message);
    }

    public GenericFiException(String message, Throwable cause){
        super(message,cause);
    }

}
