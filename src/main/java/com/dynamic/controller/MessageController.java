package com.dynamic.controller;

import com.dynamic.aopservice.MessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Api(value = "获取消息信息")
@Slf4j
public class MessageController {


    @Autowired
    MessageService messageService;

    @RequestMapping(value = "/getMessage", method = RequestMethod.POST)
    @ApiOperation(value = "getMessage")
    public String getMessage() {
        log.info("in ---------------------------");
        String message = messageService.getMessage(null);
        log.info("--------------------------- out");
        return message;
    }

}
