package com.spzx.service.user.service;

import com.spzx.model.dto.h5.UserLoginDto;
import com.spzx.model.dto.h5.UserRegisterDto;
import com.spzx.model.vo.h5.UserInfoVo;

/**
 * ClassName: UserInfoService
 * Package: com.spzx.service.user.service
 * Description:
 * Author: zhj
 * Create: 2024/4/19 - 21:26
 * Version: v1.0
 */
public interface UserInfoService {
    void register(UserRegisterDto userRegisterDto);

    String login(UserLoginDto userLoginDto);

    UserInfoVo getCurrentUserInfo(String token);
}
