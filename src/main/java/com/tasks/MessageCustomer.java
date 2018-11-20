package com.tasks;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.annotation.JmsListeners;
import org.springframework.stereotype.Component;

@Component
public class MessageCustomer {

    @JmsListener(destination = "JmsList")
    public void receive(String message){

    }
}
