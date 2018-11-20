package com.tasks.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.Queue;
import javax.jms.Topic;

@Configuration
public class ActiveMQConfig {
    @Value("${queueName}")
    private String queueList;
    @Value("${topicName}")
    private String topicList;
    @Value("${spring.activemq.user}")
    private String userName;
    @Value("${spring.activemq.password}")
    private String password;
    @Value("${spring.activemq.broker-url}")
    private String brokerUrl;

    @Bean
    public Queue queue(){
        return new ActiveMQQueue(queueList);
    }

    @Bean
    public Topic topic(){
        return new ActiveMQTopic(topicList);
    }

    @Bean
    public ActiveMQConnectionFactory activeMQConnectionFactory(){
        return new ActiveMQConnectionFactory(userName,password,brokerUrl);
    }

    /**
     * 定义JmsTemplate
     * @param activeMQConnectionFactory
     * @param queue
     * @return
     */
    @Bean
    public JmsTemplate jmsTemplate(ActiveMQConnectionFactory activeMQConnectionFactory,Queue queue){
        JmsTemplate jmsTemplate=new JmsTemplate();
        jmsTemplate.setDeliveryMode(2);//进行持久化配置 1表示非持久化，2表示持久化
        jmsTemplate.setConnectionFactory(activeMQConnectionFactory);
        jmsTemplate.setDefaultDestination(queue);
        jmsTemplate.setSessionTransacted(false);
        return jmsTemplate;
    }

//    @Bean
//    public CachingConnectionFactory cachingConnectionFactory(ActiveMQConnectionFactory activeMQConnectionFactory){
//        CachingConnectionFactory cachingConnectionFactory=new CachingConnectionFactory();
//        cachingConnectionFactory.setTargetConnectionFactory(activeMQConnectionFactory);
//        cachingConnectionFactory.setSessionCacheSize(10);
//        return cachingConnectionFactory;
//    }

    /**
     * 定义一个消息监听器连接工厂
     * @param activeMQConnectionFactory
     * @return
     */
    @Bean(name = "jmsQueueListener")
    public JmsListenerContainerFactory jmsListenerContainerFactory(ActiveMQConnectionFactory activeMQConnectionFactory){
        DefaultJmsListenerContainerFactory jmsListenerContainerFactory = new DefaultJmsListenerContainerFactory();
        jmsListenerContainerFactory.setConnectionFactory(activeMQConnectionFactory);
        jmsListenerContainerFactory.setConcurrency("1-10");
        return jmsListenerContainerFactory;
    }

}
