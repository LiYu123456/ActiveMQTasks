package com.tasks.task1;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQMapMessage;
import org.apache.activemq.command.ActiveMQMessage;

import javax.jms.*;

public class JMSCustomer {

    private static final String USERNAME = ActiveMQConnection.DEFAULT_USER;//默认的连接用户名
    private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;//默认的连接密码
    private static final String BROKERURL = ActiveMQConnection.DEFAULT_BROKER_URL;//默认的连接地址

    public static void main(String[] args) throws JMSException {
        ConnectionFactory connectionFactory=new ActiveMQConnectionFactory(JMSCustomer.USERNAME,
                JMSCustomer.PASSWORD,JMSCustomer.BROKERURL);
        Connection connection=connectionFactory.createConnection();
        connection.start();
        Session session=connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Destination destination=session.createQueue("TestQueue");
        MessageConsumer messageConsumer=session.createConsumer(destination);
        while(true){
            MapMessage mapMessage=(MapMessage)messageConsumer.receive(10000);
            if(mapMessage!=null){
                System.out.println("收到消息:"+((ActiveMQMapMessage)mapMessage).getContentMap());
            }else{
                System.out.println("没有收到消息");
            }
        }
    }
}
