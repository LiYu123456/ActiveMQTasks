package com.tasks.service;

public interface MsgService {

    /**
     * 发送消息到MQ
     * @param json
     * @return
     */
    public boolean sendMsg(String json)throws Exception;
}
