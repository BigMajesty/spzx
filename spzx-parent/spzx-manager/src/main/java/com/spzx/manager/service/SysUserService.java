package com.spzx.manager.service;

import com.github.pagehelper.PageInfo;
import com.spzx.model.dto.system.AssginRoleDto;
import com.spzx.model.dto.system.LoginDto;
import com.spzx.model.dto.system.SysUserDto;
import com.spzx.model.entity.system.SysUser;
import com.spzx.model.vo.system.LoginVo;

/**
 * ClassName: SysUserService
 * Package: com.spzx.manager.service
 * Description:
 * Author: zhj
 * Create: 2024/4/11 - 19:20
 * Version: v1.0
 */
public interface SysUserService {
    //登录方法
    LoginVo login(LoginDto loginDto);

    SysUser getUserInfo(String token);

    void logout(String token);

    PageInfo<SysUser> findByPage(Integer pageNum, Integer pageSize, SysUserDto sysUserDto);

    void saveSysUser(SysUser sysUser);

    void updateSysUser(SysUser sysUser);

    void deleteById(Long userId);

    void doAssign(AssginRoleDto assginRoleDto);
}
