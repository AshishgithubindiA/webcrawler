package com.humane.exception;

import java.io.IOException;

/**
 * Created by ashish on 23/03/20.
 */
public class JsoupHandleException extends RuntimeException {

    public JsoupHandleException(String message,Throwable err){
        super(message, err);
    }

    public JsoupHandleException(String message){
        super(message);
    }
}
