package com.tasks.service.impl;

import com.tasks.service.MsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class MsgServiceImpl implements MsgService {

    @Autowired
    private JmsMessagingTemplate jmsTemplate;

    @Override
    public boolean sendMsg(String json) throws Exception{
        jmsTemplate.convertAndSend("testQueue",json);
        return false;
    }
}
