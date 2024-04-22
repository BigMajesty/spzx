package com.spzx.manager.service;

import com.github.pagehelper.PageInfo;
import com.spzx.manager.mapper.SysRoleMapper;
import com.spzx.model.dto.system.LoginDto;
import com.spzx.model.dto.system.SysRoleDto;
import com.spzx.model.entity.system.SysRole;
import com.spzx.model.entity.system.SysUser;
import com.spzx.model.vo.system.LoginVo;

import java.util.Map;

/**
 * ClassName: SysUserService
 * Package: com.spzx.manager.service
 * Description:
 * Author: zhj
 * Create: 2024/4/11 - 19:20
 * Version: v1.0
 */
public interface SysRoleService {


    PageInfo<SysRole> findByPage(SysRoleDto sysRoleDto, Integer current, Integer limit);

    void saveSysRole(SysRole sysRole);

    void updateSysRole(SysRole sysRole);

    void deleteById(Long roleId);

    Map<String, Object> findAllRoles(Long userId);
}
