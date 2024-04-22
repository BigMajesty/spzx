package com.spzx.manager.service.impl;

import com.spzx.common.exception.GlobalException;
import com.spzx.manager.mapper.SysMenuMapper;
import com.spzx.manager.mapper.SysRoleMenuMapper;
import com.spzx.manager.mapper.SysUserRoleMapper;
import com.spzx.manager.service.SysMenuService;
import com.spzx.manager.utils.BuildMenuTreeUtil;
import com.spzx.model.entity.system.SysMenu;
import com.spzx.model.entity.system.SysUser;
import com.spzx.model.vo.common.ResultCodeEnum;
import com.spzx.model.vo.system.SysMenuVo;
import com.spzx.utils.AuthContextUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * ClassName: SysMenuServiceImpl
 * Package: com.spzx.manager.service.impl
 * Description:
 * Author: zhj
 * Create: 2024/4/13 - 19:31
 * Version: v1.0
 */
@Service
public class SysMenuServiceImpl implements SysMenuService {
    @Autowired
    SysMenuMapper sysMenuMapper;
    @Autowired
    SysUserRoleMapper sysUserRoleMapper;
    @Autowired
    SysRoleMenuMapper sysRoleMenuMapper;

    /**
     * 菜单列表
     * @return
     */
    @Override
    public List<SysMenu> findNodes() {
        //查询菜单列表
        List<SysMenu> list = sysMenuMapper.findNodes();
        //根据查询到的数据，进行分级
        List<SysMenu> treeList = BuildMenuTreeUtil.buildTree(list, 0L);
        return treeList;
    }

    /**
     * 添加菜单
     * @param sysMenu
     */
    @Override
    public void save(SysMenu sysMenu) {
        sysMenuMapper.addMenu(sysMenu);
    }

    /**
     * 更新菜单
     * @param sysMenu
     */
    @Override
    public void updateById(SysMenu sysMenu) {
        sysMenuMapper.updateById(sysMenu);
    }

    /**
     * 删除菜单
     * @param id
     */
    @Override
    public void removeById(Long id) {
        //查询是否存在子菜单
        int count=sysMenuMapper.countByParentId(id);
        //若存在子菜单，不允许删除，抛出异常
        if(count>0){
            throw new GlobalException(ResultCodeEnum.NODE_ERROR);
        }
        sysMenuMapper.removeById(id);
    }

    /**
     * 动态菜单
     * @return
     */
    @Override
    public List<SysMenuVo> findUserMenuList() {
        //通过ThreadLocal获取当前用户id
        SysUser sysUser = AuthContextUtil.get();
        Long userId = sysUser.getId();
        //根据用户id查询用户角色
        List<Long> userRoleIds = sysUserRoleMapper.findSysUserRoleByUserId(userId);
        //根据用户角色查询用户具有的菜单
        List<SysMenu> userMenus= sysRoleMenuMapper.getMenuByRoleId(userRoleIds);
        userMenus = BuildMenuTreeUtil.buildTree(userMenus,0L);
        return buildMenus(userMenus);
    }
    // 将List<SysMenu>对象转换成List<SysMenuVo>对象
    private List<SysMenuVo> buildMenus(List<SysMenu> menus) {
        List<SysMenuVo> sysMenuVoList = new LinkedList<SysMenuVo>();
        for (SysMenu sysMenu : menus) {
            SysMenuVo sysMenuVo = new SysMenuVo();
            sysMenuVo.setTitle(sysMenu.getTitle());
            sysMenuVo.setName(sysMenu.getComponent());
            List<SysMenu> children = sysMenu.getChildren();
            if (!CollectionUtils.isEmpty(children)) {
                sysMenuVo.setChildren(buildMenus(children));
            }
            sysMenuVoList.add(sysMenuVo);
        }
        return sysMenuVoList;
    }
}
