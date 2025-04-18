package com.scaler.productservicecb.exceptions;

public class DBTimeoutException extends Exception{
    public DBTimeoutException(String message){
        super(message);
    }
}
