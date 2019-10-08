package com.dynamic.service;

import com.dynamic.entity.UserInfo;
import com.dynamic.mapper.UserInfoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class UserInfoService {

    @Resource
    private UserInfoMapper userInfoMapper;


    public List<UserInfo> slaveList() {
        return userInfoMapper.getUserInfoList();
    }


    public List<UserInfo> masterList() {
        return userInfoMapper.getUserInfoList();
    }


    /**
     * 添加数据，有事务
     * 添加事务管理
     *
     * @param user
     * @return
     */
    @Transactional
    public void masterInsertUser(UserInfo user) {
        userInfoMapper.insertUser(user);
    }


    /**
     * 添加数据，没有事务
     * 添加事务管理
     *
     * @param user
     * @return
     */
    @Transactional
    public void slave1InsertUser(UserInfo user) {
        userInfoMapper.insertUser(user);
    }


    /**
     * 添加数据，没有事务
     * 添加事务管理
     *
     * @param user
     * @return
     */
    @Transactional
    public void slave2InsertUser(UserInfo user) {
        userInfoMapper.insertUser(user);
    }

    /**
     * 添加数据，有事务
     * 添加事务管理
     *
     * @param user
     * @return
     */
    public void masterNoTxInsertUser(UserInfo user) {
        userInfoMapper.insertUser(user);
    }


    /**
     * 添加数据，没有事务
     * 添加事务管理
     *
     * @param user
     * @return
     */
    public void slave1NoTxInsertUser(UserInfo user) {
        userInfoMapper.insertUser(user);
    }

    /**
     * 添加数据，没有事务
     * 添加事务管理
     *
     * @param user
     * @return
     */
    public void slave2NoTxInsertUser(UserInfo user) {
        userInfoMapper.insertUser(user);
    }
}
