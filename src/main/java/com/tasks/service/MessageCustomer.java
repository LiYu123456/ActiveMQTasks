package com.tasks.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.annotation.JmsListeners;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class MessageCustomer {

    @JmsListener(destination ="testQueue1,testQueue2?consumer.prefetchSize=10",containerFactory = "jmsQueueListener")
    public void receive1(Map<String,String> message) throws InterruptedException {
        System.out.println("消费者1====收到的内容："+message);
        Thread.sleep(10000);
    }

    @JmsListener(destination ="testQueue1,testQueue2?consumer.prefetchSize=10",containerFactory = "jmsQueueListener")
    public void receive2(Map<String,String> message) throws InterruptedException {
        System.out.println("消费者2====收到的内容："+message);
        Thread.sleep(10000);
    }
}
