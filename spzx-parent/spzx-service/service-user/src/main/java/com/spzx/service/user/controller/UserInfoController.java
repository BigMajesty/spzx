package com.spzx.service.user.controller;

import com.spzx.model.dto.h5.UserLoginDto;
import com.spzx.model.dto.h5.UserRegisterDto;
import com.spzx.model.vo.common.Result;
import com.spzx.model.vo.common.ResultCodeEnum;
import com.spzx.model.vo.h5.UserInfoVo;
import com.spzx.service.user.service.UserInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * ClassName: UserInfoController
 * Package: com.spzx.service.user.controller
 * Description:
 * Author: zhj
 * Create: 2024/4/19 - 21:25
 * Version: v1.0
 */
@Tag(name = "会员用户接口")
@RestController
@RequestMapping("api/user/userInfo")
public class UserInfoController {
    @Autowired
    UserInfoService userInfoService;

    /**
     * 会员注册
     * @param userRegisterDto
     * @return
     */
    @Operation(summary = "会员注册")
    @PostMapping("register")
    public Result register(@RequestBody UserRegisterDto userRegisterDto) {
        userInfoService.register(userRegisterDto);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    /**
     * 用户登录
     * @param userLoginDto
     * @return
     */
    @PostMapping("/login")
    public Result login(@RequestBody UserLoginDto userLoginDto){
        String token = userInfoService.login(userLoginDto);
        return Result.build(token,ResultCodeEnum.SUCCESS);
    }

    /**
     * 登录后获取用户信息
     * @return
     */
    @GetMapping("/auth/getCurrentUserInfo")
    public Result getCurrentUserInfo(HttpServletRequest httpServletRequest){
        String token = httpServletRequest.getHeader("token");
        UserInfoVo userInfoVo = userInfoService.getCurrentUserInfo(token);
        return Result.build(userInfoVo,ResultCodeEnum.SUCCESS);
    }
}
