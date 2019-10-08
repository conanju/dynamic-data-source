package com.dynamic.services;

import com.dynamic.entity.UserInfo;
import com.dynamic.service.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Slf4j
public class UserOperateServices {

    @Resource
    private UserInfoService userInfoService;


    /**
     * 动态库切换没有添加@Order，会导致事务先执行，切库失败 ！
     * @param user
     * @return
     */
    public String insertUser(UserInfo user) {
        userInfoService.masterInsertUser(user);
        userInfoService.slave1InsertUser(user);
        userInfoService.slave2InsertUser(user);
        return "success";
    }


    /**
     * 没有事务则可以切库
     * @param user
     * @return
     */
    public String noTxInsertUser(UserInfo user) {
        userInfoService.masterNoTxInsertUser(user);
        userInfoService.slave1NoTxInsertUser(user);
        userInfoService.slave2NoTxInsertUser(user);
        return "success";
    }


    /**
     * 没有事务则可以切库
     * @param user
     * @return
     */
    public String secondTxInsertUser(UserInfo user) {
        userInfoService.masterNoTxInsertUser(user);
        userInfoService.slave1InsertUser(user);
        userInfoService.slave2InsertUser(user);
        return "success";
    }

    /**
     * 没有事务则可以切库
     * @param user
     * @return
     */
    public String firstTxInsertUser(UserInfo user) {
        userInfoService.masterInsertUser(user);
        userInfoService.slave1NoTxInsertUser(user);
        userInfoService.slave2InsertUser(user);
        return "success";
    }

}
