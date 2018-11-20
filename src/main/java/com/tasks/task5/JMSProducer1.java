package com.tasks.task5;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class JMSProducer1 {

    private static final String USERNAME = ActiveMQConnection.DEFAULT_USER;//默认的连接用户名
    private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;//默认的连接密码
    private static final String BROKERURL = ActiveMQConnection.DEFAULT_BROKER_URL;//默认的连接地址

    public static void main(String args[]){
        ConnectionFactory connectionFactory=null;
        Connection connection=null;
        Session session=null;
        Destination destination=null;
        MessageProducer messageProducer=null;

        connectionFactory=new ActiveMQConnectionFactory(JMSProducer1.USERNAME, JMSProducer1.PASSWORD,
                JMSProducer1.BROKERURL);
        try {
            connection=connectionFactory.createConnection();
            connection.start();
            session=connection.createSession(true,Session.AUTO_ACKNOWLEDGE);
            destination=session.createQueue("TestQueue1");
            messageProducer=session.createProducer(destination);
            for(int i=0;i<10;i++) {
                sendMessage(i,session,messageProducer);
                session.commit();
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }finally{
            if(connection!=null){
                try {
                    connection.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        }

    }
    private static void sendMessage(int i,Session session,MessageProducer messageProducer) throws JMSException {

            MapMessage message=session.createMapMessage();
            message.setString("key","生产者1");
            message.setString("userName","张三");
            message.setInt("age",i);
            messageProducer.send(message);

    }
}
