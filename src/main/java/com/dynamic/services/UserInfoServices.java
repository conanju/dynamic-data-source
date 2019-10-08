package com.dynamic.services;

import com.dynamic.common.constant.Constants;
import com.dynamic.config.ManualChangeDataSourceConfig;
import com.dynamic.entity.UserInfo;
import com.dynamic.mapper.UserInfoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class UserInfoServices {

    @Resource
    private UserInfoMapper userInfoMapper;

    @Resource
    private ManualChangeDataSourceConfig config;

    public void addUserMaster(UserInfo user) {
        config.setDataSourceKey(Constants.MASTER_DATABASE);
        userInfoMapper.insertUser(user);

    }


    public void addUserSlave(UserInfo user) {
        config.setDataSourceKey(Constants.SLAVE_DATABASE_1);
        userInfoMapper.insertUserError(user);

    }


}
