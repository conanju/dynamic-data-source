package com.dynamic.controller;

import com.dynamic.entity.UserInfo;
import com.dynamic.services.UserInfoServices;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Api(value = "用户-手动切库")
public class UserManualController {
    @Autowired
    UserInfoServices permissionServices;

    @GetMapping("/addUser/{uname}/{mail}")
    @ApiOperation(value = "addUser")
    public String addUser(@PathVariable String uname,
                          @PathVariable String mail) {
        UserInfo user = new UserInfo();
        user.setUname(uname);
        user.setMail(mail);

        permissionServices.addUserMaster(user);
        permissionServices.addUserSlave(user);

        return "success";
    }

}
