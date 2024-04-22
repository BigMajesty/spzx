package com.spzx.manager.mapper;

import com.github.pagehelper.PageInfo;
import com.spzx.model.dto.system.SysRoleDto;
import com.spzx.model.entity.system.SysRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * ClassName: SysRoleMapper
 * Package: com.spzx.manager.mapper
 * Description:
 * Author: zhj
 * Create: 2024/4/12 - 19:07
 * Version: v1.0
 */
@Mapper
public interface SysRoleMapper {

    //查询用户列表
    List<SysRole>  findByPage(SysRoleDto sysRoleDto);
    //新增角色
    void saveSysRole(SysRole sysRole);
    //更新角色
    void updateSysRole(SysRole sysRole);
    //删除角色：逻辑删除
    void deleteById(Long roleId);
    //查询所有角色
    List<SysRole> findAllRoles();
}
