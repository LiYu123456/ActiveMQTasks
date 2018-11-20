package com.tasks.service.impl;

import com.tasks.service.MsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.SessionCallback;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.Session;

@Service
public class MsgServiceImpl implements MsgService {

    @Autowired
    private JmsTemplate jmsTemplate;
    @Autowired
    private Queue queue;

    /**
     * 发送消息到队列
     * @param json  要发送的json串
     * @return
     * @throws Exception
     */
    @Override
    public boolean sendMsg(String json) throws Exception{
        jmsTemplate.execute(new SessionCallback<Object>() {
            @Override
            public Object doInJms(Session session) throws JMSException {
                return null;
            }
        });
        return true;
    }
}
