package com.dynamic.controller;

import com.dynamic.entity.UserInfo;
import com.dynamic.service.UserInfoService;
import com.dynamic.services.UserOperateServices;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@Api(value = "用户")
@Slf4j
public class UserInfoController {

    @Autowired
    UserOperateServices userOperateServices;

    @Autowired
    UserInfoService userInfoService;


    @PostMapping("/insertUser")
    @ApiOperation(value = "insertUser")
    public String insertUser(@RequestParam String uname,
                             @RequestParam String mail) {
        UserInfo user = new UserInfo();
        user.setUname(uname);
        user.setMail(mail);
        userOperateServices.insertUser(user);
        return "true";
    }

    //没有事务
    @PostMapping("/noTxInsertUser")
    @ApiOperation(value = "noTxInsertUser")
    public String insertTranUser(@RequestParam String uname,
                                 @RequestParam String mail) {
        UserInfo user = new UserInfo();
        user.setUname(uname);
        user.setMail(mail);
        return userOperateServices.noTxInsertUser(user);
    }


    @GetMapping("/secondTxInsertUser/{uname}/{mail}")
    @ApiOperation(value = "secondTxInsertUser")
    public String secondTxInsertUser(@PathVariable String uname,
                                     @PathVariable String mail) {
        UserInfo user = new UserInfo();
        user.setUname(uname);
        user.setMail(mail);
        return userOperateServices.secondTxInsertUser(user);
    }

    @GetMapping("/firstTxInsertUser/{uname}/{mail}")
    @ApiOperation(value = "secondTxInsertUser")
    public String firstTxInsertUser(@PathVariable String uname,
                                     @PathVariable String mail) {
        UserInfo user = new UserInfo();
        user.setUname(uname);
        user.setMail(mail);
        return userOperateServices.firstTxInsertUser(user);
    }

}
