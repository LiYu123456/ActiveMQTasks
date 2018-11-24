package com.tasks.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.annotation.JmsListeners;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class MessageCustomer {

    /**
     * 这种方式可以实现队列的动态配置，但是需要重启
     * @param message
     * @throws InterruptedException
     */
    @JmsListener(destination ="${queueName}",containerFactory = "jmsQueueListener")
    public void receive1(Map<String,String> message) throws InterruptedException {
        System.out.println("消费者1====收到的内容："+message);
        Thread.sleep(10000);
    }

    @JmsListener(destination ="${queueName}",containerFactory = "jmsQueueListener")
    public void receive2(Map<String,String> message) throws InterruptedException {
        System.out.println("消费者2====收到的内容："+message);
        Thread.sleep(10000);
    }
}
