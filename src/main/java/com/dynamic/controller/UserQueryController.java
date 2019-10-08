package com.dynamic.controller;

import com.dynamic.entity.UserInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@Api(value = "用户查询")
public class UserQueryController {


    @Autowired
    com.dynamic.service.UserInfoService userInfoService;

    @RequestMapping(value = "/dataBaseMaster", method = RequestMethod.POST)
    @ApiOperation(value = "dataBaseMaster")
    public List<UserInfo> dataBaseMaster() {
        return userInfoService.masterList();
    }

    @RequestMapping(value = "/dataBaseSlave", method = RequestMethod.POST)
    @ApiOperation(value = "dataBaseSlave")
    public List<UserInfo> dataBaseSlave() {
        return userInfoService.slaveList();
    }


}
