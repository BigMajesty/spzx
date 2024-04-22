package com.spzx.manager.controller;

import com.spzx.manager.service.SysMenuService;
import com.spzx.manager.service.SysUserService;
import com.spzx.manager.service.ValidateCodeService;
import com.spzx.model.dto.system.LoginDto;
import com.spzx.model.entity.system.SysUser;
import com.spzx.model.vo.common.Result;
import com.spzx.model.vo.common.ResultCodeEnum;
import com.spzx.model.vo.system.LoginVo;
import com.spzx.model.vo.system.SysMenuVo;
import com.spzx.model.vo.system.ValidateCodeVo;
import com.spzx.utils.AuthContextUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ClassName: IndexController
 * Package: com.spzx.manager.controller
 * Description:
 * Author: zhj
 * Create: 2024/4/11 - 19:17
 * Version: v1.0
 */
//swagger接口测试的注解
@Tag(name = "用户接口")
@RestController
@RequestMapping(value = "/admin/system/index")
public class IndexController {
    @Autowired
    SysUserService sysUserService;
    @Autowired
    private ValidateCodeService validateCodeService;
    @Autowired
    SysMenuService sysMenuService;

    //获取当前登录用户信息
    @GetMapping("/getUserInfo")
    public Result getUserInfo(@RequestHeader(name = "token") String token){
        //从请求头获取token
        // String token = httpServletRequest.getHeader("token");
        //根据token查询redis用户信息
        //SysUser sysUser= sysUserService.getUserInfo(token);
        //通过ThreadLocal直接获取用户信息
        SysUser sysUser= AuthContextUtil.get();
        //用户信息返回
        return Result.build(sysUser,ResultCodeEnum.SUCCESS);
    }


    //生成图片验证码
    @GetMapping(value = "/generateValidateCode")
    public Result<ValidateCodeVo> generateValidateCode(){
        ValidateCodeVo validateCodeVo = validateCodeService.generateValidateCode();
        return Result.build(validateCodeVo,ResultCodeEnum.SUCCESS);
    }

    //swagger测试，中文提示
    @Operation(summary = "登录的方法")
    //用户登录
    @PostMapping("/login")
    public Result<LoginVo> login(@RequestBody LoginDto loginDto){//loginDto用于封装提交过来的数据
        LoginVo loginVo = sysUserService.login(loginDto);
        return Result.build(loginVo, ResultCodeEnum.SUCCESS);
    }

    //用户退出
    @GetMapping("/logout")
    public Result  logout(@RequestHeader(name = "token") String token){
        sysUserService.logout(token);
        return Result.build(null,ResultCodeEnum.SUCCESS);
    }

    /**
     * 动态菜单
     * @return
     */
    @GetMapping("/menus")
    public Result menus() {
        List<SysMenuVo> sysMenuVoList =  sysMenuService.findUserMenuList() ;
        return Result.build(sysMenuVoList , ResultCodeEnum.SUCCESS) ;
    }

}

