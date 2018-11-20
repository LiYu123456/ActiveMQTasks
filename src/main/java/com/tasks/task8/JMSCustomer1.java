package com.tasks.task8;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.broker.BrokerService;
import org.apache.activemq.broker.jmx.BrokerViewMBean;
import org.apache.activemq.broker.jmx.QueueViewMBean;
import org.apache.activemq.command.ActiveMQMapMessage;

import javax.jms.*;
import javax.management.MBeanServerConnection;
import javax.management.MBeanServerInvocationHandler;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import java.io.IOException;
import java.net.MalformedURLException;

public class JMSCustomer1 {

    //jmx服务地址，注意端口
    static String jmxserviceURL="service:jmx:rmi:///jndi/rmi://localhost:1099/jmxrmi";
    //brokerName和type首字母要小些，brokerName=broker要与上面配置文件一致，斜体下划线部分
    static String objectName="org.apache.activemq:brokerName=localhost,type=Broker";

    public static void main(String[] args) throws MalformedObjectNameException, IOException {
        JMXServiceURL url = new JMXServiceURL(jmxserviceURL);
        JMXConnector connector = JMXConnectorFactory.connect(url, null);
        connector.connect();
        MBeanServerConnection connection = connector.getMBeanServerConnection();

        ObjectName name = new ObjectName(objectName);
        BrokerViewMBean mBean =  (BrokerViewMBean)MBeanServerInvocationHandler.newProxyInstance(connection,
                name, BrokerViewMBean.class, true);
        // System.out.println(mBean.getBrokerName());

        for(ObjectName queueName : mBean.getQueues()) {
            QueueViewMBean queueMBean =   (QueueViewMBean)MBeanServerInvocationHandler.
                    newProxyInstance(connection, queueName, QueueViewMBean.class, true);
            System.out.println("\n------------------------------\n");

            // 消息队列名称
            System.out.println("States for queue --- " + queueMBean.getName());

            // 队列中剩余的消息数
            System.out.println("Size --- " + queueMBean.getQueueSize());

            // 消费者数
            System.out.println("Number of consumers --- " + queueMBean.getConsumerCount());

            // 出队数
            System.out.println("Number of dequeue ---" + queueMBean.getDequeueCount() );
        }
    }
}
