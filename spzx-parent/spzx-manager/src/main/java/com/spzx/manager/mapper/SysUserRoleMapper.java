package com.spzx.manager.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * ClassName: SysUserRoleMapper
 * Package: com.spzx.manager.mapper
 * Description:
 * Author: zhj
 * Create: 2024/4/13 - 17:47
 * Version: v1.0
 */
@Mapper
public interface SysUserRoleMapper {
    //删除用户角色信息
    public void delete(Long userId);
    //增加用户角色信息
    void doAssign(Long roleId, Long userId);
    //根据用户id查询用户角色
    List<Long> findSysUserRoleByUserId(Long userId);
}
