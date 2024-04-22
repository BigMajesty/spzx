package com.spzx.service.user.mapper;

import com.spzx.model.dto.h5.UserRegisterDto;
import com.spzx.model.entity.user.UserInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * ClassName: UserInfoMapper
 * Package: com.spzx.service.user.mapper
 * Description:
 * Author: zhj
 * Create: 2024/4/19 - 21:27
 * Version: v1.0
 */
@Mapper
public interface UserInfoMapper {
    //会员注册
    public void register(UserInfo userInfo);

    //根据用户名查找用户
    UserInfo selectUserInfoByUsername(String username);
}
