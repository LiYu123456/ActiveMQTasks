package com.tasks.task4;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQMapMessage;

import javax.jms.*;

public class JMSCustomer1 {

    private static final String USERNAME = ActiveMQConnection.DEFAULT_USER;//默认的连接用户名
    private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;//默认的连接密码
    private static final String BROKERURL = ActiveMQConnection.DEFAULT_BROKER_URL;//默认的连接地址

    public static void main(String[] args) throws JMSException {
        ConnectionFactory connectionFactory=new ActiveMQConnectionFactory(JMSCustomer1.USERNAME,
                JMSCustomer1.PASSWORD, JMSCustomer1.BROKERURL);
        Connection connection=connectionFactory.createConnection();
        connection.start();
        Session session=connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Destination destination=session.createQueue("TestQueue?consumer.prefetchSize=1");
        MessageConsumer messageConsumer=session.createConsumer(destination);
        messageConsumer.setMessageListener(new MessageListener() {
            public void onMessage(Message message) {
                if(message!=null){
                    try {
                        System.out.println("消费者1收到消息:"+((ActiveMQMapMessage)message).getContentMap());
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }else{
                    System.out.println("没有收到消息");
                }
            }
        });
    }
}
