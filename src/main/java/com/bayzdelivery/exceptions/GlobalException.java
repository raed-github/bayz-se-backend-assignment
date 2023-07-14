package com.bayzdelivery.exceptions;

/**
 * defining a Global exception class that extends Runtime exception.
 * @author Raed
 *
 */
public class GlobalException extends RuntimeException{
    private String msg;

    public GlobalException(String msg){
        super(msg);
    }
}
