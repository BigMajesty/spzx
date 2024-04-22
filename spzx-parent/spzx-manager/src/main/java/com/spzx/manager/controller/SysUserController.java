package com.spzx.manager.controller;

import com.github.pagehelper.PageInfo;
import com.spzx.manager.service.SysUserService;
import com.spzx.model.dto.system.AssginRoleDto;
import com.spzx.model.dto.system.SysUserDto;
import com.spzx.model.entity.system.SysUser;
import com.spzx.model.vo.common.Result;
import com.spzx.model.vo.common.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * ClassName: SysUserController
 * Package: com.spzx.manager.controller
 * Description:
 * Author: zhj
 * Create: 2024/4/13 - 9:40
 * Version: v1.0
 */
@RestController
@RequestMapping(value = "/admin/system/sysUser")
public class SysUserController {
    @Autowired
    SysUserService sysUserService;

    /**
     * 查询用户列表
     * @param pageNum
     * @param pageSize
     * @param sysUserDto
     * @return
     */
    @GetMapping(value = "/findByPage/{pageNum}/{pageSize}")
    public Result findByPage(@PathVariable(name = "pageNum")Integer pageNum,
                             @PathVariable(name = "pageSize")Integer pageSize,
                             SysUserDto sysUserDto){
        PageInfo<SysUser> pageInfo = sysUserService.findByPage(pageNum,pageSize,sysUserDto);
        return Result.build(pageInfo, ResultCodeEnum.SUCCESS);
    }

    /**
     * 增加用户
     * @param sysUser
     * @return
     */
    @PostMapping(value = "/saveSysUser")
    public Result saveSysUser(@RequestBody SysUser sysUser) {
        sysUserService.saveSysUser(sysUser);
        return Result.build(null,ResultCodeEnum.SUCCESS);
    }

    /**
     * 修改用户信息
     * @param sysUser
     * @return
     */
    @PutMapping(value = "/updateSysUser")
    public Result updateSysUser(@RequestBody SysUser sysUser) {
        sysUserService.updateSysUser(sysUser);
        return Result.build(null,ResultCodeEnum.SUCCESS);
    }

    /**
     * 删除用户，逻辑删除
     * @param userId
     * @return
     */
    @DeleteMapping(value = "/deleteById/{userId}")
    public Result deleteById(@PathVariable(value = "userId") Long userId) {
        sysUserService.deleteById(userId);
        return Result.build(null,ResultCodeEnum.SUCCESS);
    }

    /**
     * 保存角色数据
     * @param assginRoleDto
     * @return
     */
    @PostMapping("/doAssign")
    public Result doAssign(@RequestBody AssginRoleDto assginRoleDto) {
        sysUserService.doAssign(assginRoleDto);
        return Result.build(null,ResultCodeEnum.SUCCESS);
    }
}
