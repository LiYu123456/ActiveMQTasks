package com.tasks.task3;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQMapMessage;

import javax.jms.*;

public class JMSCustomer2 {

    private static final String USERNAME = ActiveMQConnection.DEFAULT_USER;//默认的连接用户名
    private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;//默认的连接密码
    private static final String BROKERURL = ActiveMQConnection.DEFAULT_BROKER_URL;//默认的连接地址

    public static void main(String[] args) throws JMSException {
        ConnectionFactory connectionFactory=new ActiveMQConnectionFactory(JMSCustomer2.USERNAME,
                JMSCustomer2.PASSWORD, JMSCustomer2.BROKERURL);
        Connection connection=connectionFactory.createConnection();
        connection.setClientID("client2");
        connection.start();
        Session session=connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Topic topic=session.createTopic("myFirstTopic?consumer.prefetchSize=1");
        MessageConsumer messageConsumer=session.createDurableSubscriber(topic,"消费者2");
        messageConsumer.setMessageListener(new MessageListener() {
            public void onMessage(Message message) {
                try {
                    System.out.println("订阅者二收到的消息："+((ActiveMQMapMessage)message).getContentMap());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
