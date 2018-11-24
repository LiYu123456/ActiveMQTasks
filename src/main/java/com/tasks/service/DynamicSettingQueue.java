package com.tasks.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.AbstractEnvironment;

import javax.annotation.PostConstruct;

//@Configuration
public class DynamicSettingQueue {
    public static final String dynamicConfigQueueName="queueName";
    @Autowired
    private AbstractEnvironment environment;

    @PostConstruct
    public void init(){
        environment.getPropertySources().addFirst(new DynamicLoadPropertySource(dynamicConfigQueueName,null));
    }
}
