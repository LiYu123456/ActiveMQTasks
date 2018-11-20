package com.tasks.task3;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class JMSProducer {

    private static final String USERNAME = ActiveMQConnection.DEFAULT_USER;//默认的连接用户名
    private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;//默认的连接密码
    private static final String BROKERURL = ActiveMQConnection.DEFAULT_BROKER_URL;//默认的连接地址

    public static void main(String args[]){
        ConnectionFactory connectionFactory=null;
        Connection connection=null;
        Session session=null;
        Destination destination=null;
        MessageProducer messageProducer=null;

        connectionFactory=new ActiveMQConnectionFactory(JMSProducer.USERNAME, JMSProducer.PASSWORD,
                JMSProducer.BROKERURL);
        try {
            connection=connectionFactory.createConnection();
            connection.start();
            session=connection.createSession(true,Session.AUTO_ACKNOWLEDGE);
            destination=session.createTopic("myFirstTopic");
            messageProducer=session.createProducer(destination);
            messageProducer.setDeliveryMode(DeliveryMode.PERSISTENT);
            sendMessage(session,messageProducer);
            session.commit();
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
    private static void sendMessage(Session session,MessageProducer messageProducer) throws JMSException {
        MapMessage message=session.createMapMessage();
        message.setString("userName","张三");
        message.setInt("age",30);
        messageProducer.send(message);
    }
}
