package com.tasks.task8;

import com.tasks.task1.JMSCustomer;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ScheduledMessage;
import org.apache.activemq.broker.jmx.BrokerViewMBean;
import org.apache.activemq.broker.jmx.QueueViewMBean;
import org.apache.activemq.command.ActiveMQMapMessage;
import org.apache.activemq.command.ActiveMQMessage;

import javax.jms.*;
import javax.management.MBeanServerConnection;
import javax.management.MBeanServerInvocationHandler;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import javax.naming.Context;
import java.io.IOException;
import java.util.Enumeration;

public class JMSCustomer2 {

    private static final String USERNAME = ActiveMQConnection.DEFAULT_USER;//默认的连接用户名
    private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;//默认的连接密码
    private static final String BROKERURL = ActiveMQConnection.DEFAULT_BROKER_URL;//默认的连接地址

    public static void main(String[] args) throws MalformedObjectNameException, IOException, JMSException {
        ConnectionFactory connectionFactory=new ActiveMQConnectionFactory(JMSCustomer2.USERNAME,
                JMSCustomer2.PASSWORD,JMSCustomer2.BROKERURL);
        Connection connection=connectionFactory.createConnection();
        connection.start();
        Session session=connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Queue queue=session.createQueue("TestQueue1");
        session.createObjectMessage();
        Enumeration enumeration=session.createBrowser(queue).getEnumeration();
        while(enumeration.hasMoreElements()){
            ActiveMQMessage message=(ActiveMQMessage)enumeration.nextElement();

            System.out.println(message.getJMSMessageID());
        }
        connection.close();
    }
}
