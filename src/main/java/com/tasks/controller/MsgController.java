package com.tasks.controller;

import com.tasks.service.MsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/msg")
public class MsgController {
    @Autowired
    private MsgService msgService;

    @RequestMapping("/sendMsg")
    public String sendMsg(String msg){
        msgService.sendMsg(msg);
        return  "success";
    }

}
