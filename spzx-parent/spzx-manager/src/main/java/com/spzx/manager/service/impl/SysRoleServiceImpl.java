package com.spzx.manager.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.spzx.manager.mapper.SysRoleMapper;
import com.spzx.manager.mapper.SysUserRoleMapper;
import com.spzx.manager.service.SysRoleService;
import com.spzx.model.dto.system.SysRoleDto;
import com.spzx.model.entity.system.SysRole;
import com.spzx.model.vo.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ClassName: SysRoleServiceImpl
 * Package: com.spzx.manager.service.impl
 * Description:
 * Author: zhj
 * Create: 2024/4/12 - 19:06
 * Version: v1.0
 */
@Service
public class SysRoleServiceImpl implements SysRoleService {
    @Autowired
    SysRoleMapper sysRoleMapper;

    @Autowired
    SysUserRoleMapper sysUserRoleMapper;
    /**
     * 查询角色列表并返回pageInfo
     * @param sysRoleDto
     * @param current
     * @param limit
     * @return
     */
    @Override
    public PageInfo<SysRole> findByPage(SysRoleDto sysRoleDto, Integer current, Integer limit) {
        PageHelper.startPage(current,limit);
        List<SysRole> list = sysRoleMapper.findByPage(sysRoleDto);
        PageInfo<SysRole> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    /**
     * 添加用户信息
     * @param sysRole
     */
    @Override
    public void saveSysRole(SysRole sysRole) {
        //设置对象的创建与更新时间
        sysRoleMapper.saveSysRole(sysRole);

    }

    /**
     * 修改角色信息
     * @param sysRole
     */
    @Override
    public void updateSysRole(SysRole sysRole) {
        sysRoleMapper.updateSysRole(sysRole);
    }

    /**
     * 删除角色：逻辑删除
     * @param roleId
     */
    @Override
    public void deleteById(Long roleId) {
        sysRoleMapper.deleteById(roleId);
    }

    /**
     * 查询所有角色，以及当前用户的角色
     * @return
     */
    @Override
    public Map<String, Object> findAllRoles(Long userId) {
        Map<String,Object> map = new HashMap<>();
        //查询所有角色
        List<SysRole> list = sysRoleMapper.findAllRoles();
        //查询当前用户的角色
        List<Long> sysRoles = sysUserRoleMapper.findSysUserRoleByUserId(userId);
        map.put("allRolesList",list);
        map.put("sysUserRoles",sysRoles);
        return map;
    }
}
