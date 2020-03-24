package com.humane.exception;

import javax.jms.JMSException;

/**
 * Created by ashish on 23/03/20.
 */
public class JmsHandleException extends JMSException {

    public JmsHandleException(String message){
        super(message);
    }
}
