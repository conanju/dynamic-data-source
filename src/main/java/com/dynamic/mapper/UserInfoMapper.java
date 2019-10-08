package com.dynamic.mapper;

import com.dynamic.entity.UserInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserInfoMapper {
    @Select("select uid,uname,mail from userinfo ")
    @Results({
            @Result(property = "uid", column = "uid", javaType = Integer.class),
            @Result(property = "uname", column = "uname", javaType = String.class),
            @Result(property = "mail", column = "mail", javaType = String.class)})
    public List<UserInfo> getUserInfoList();

    @Insert({"insert into userinfo(uname,mail) values(#{uname}, #{mail})"})
    int insertUser(UserInfo user);

    @Insert({"insert into userinfo(uid,uname,mail) values(#{uid},#{uname}, #{mail})"})
    int insertUserError(UserInfo user);
}
