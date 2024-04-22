package com.spzx.manager.service.impl;

import com.spzx.manager.mapper.SysRoleMenuMapper;
import com.spzx.manager.service.SysMenuService;
import com.spzx.manager.service.SysRoleMenuService;
import com.spzx.model.dto.system.AssginMenuDto;
import com.spzx.model.entity.system.SysMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ClassName: SysRoleMenuServiceImpl
 * Package: com.spzx.manager.service.impl
 * Description:
 * Author: zhj
 * Create: 2024/4/13 - 20:53
 * Version: v1.0
 */
@Service
public class SysRoleMenuServiceImpl implements SysRoleMenuService {
    @Autowired
    SysRoleMenuMapper sysRoleMenuMapper;
    @Autowired
    SysMenuService sysMenuService;
    /**
     * 查询菜单列表，已经拥有的菜单回显
     * @param roleId
     * @return
     */
    @Override
    public Map<String, Object> findSysRoleMenuByRoleId(Long roleId) {
        //查询所有菜单
        List<SysMenu> allMenuList = sysMenuService.findNodes();
        //查询已有菜单
        List<Long> roleMenuIds = sysRoleMenuMapper.findSysRoleMenuByRoleId(roleId);
        Map<String,Object> map = new HashMap<>();
        map.put("sysMenuList",allMenuList);
        map.put("roleMenuIds",roleMenuIds);
        return map;
    }

    /**
     * 为角色分配菜单
     * @param assginMenuDto
     */
    @Override
    public void doAssign(AssginMenuDto assginMenuDto) {
        //删除角色原有菜单
        sysRoleMenuMapper.deleteMenu(assginMenuDto.getRoleId());

        //新增角色菜单
        if(assginMenuDto.getMenuIdList()!=null&&assginMenuDto.getMenuIdList().size()>0){
            sysRoleMenuMapper.doAssign(assginMenuDto);
        }
    }
}
