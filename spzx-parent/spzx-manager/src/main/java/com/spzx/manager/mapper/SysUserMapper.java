package com.spzx.manager.mapper;

import com.spzx.model.dto.system.AssginRoleDto;
import com.spzx.model.dto.system.SysUserDto;
import com.spzx.model.entity.system.SysUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * ClassName: SysUserMapper
 * Package: com.spzx.manager.mapper
 * Description:
 * Author: zhj
 * Create: 2024/4/11 - 19:21
 * Version: v1.0
 */
@Mapper
public interface SysUserMapper {

    //根据用户名查询用户
    SysUser selectUserInfoByUserName(String userName);
    //查询用户列表
    List<SysUser> findByPage(SysUserDto sysUserDto);
    //新增用户
    void saveSysUser(SysUser sysUser);

    //更新用户信息
    void updateSysUser(SysUser sysUser);

    //删除用户，逻辑删除
    void deleteById(Long userId);

}
