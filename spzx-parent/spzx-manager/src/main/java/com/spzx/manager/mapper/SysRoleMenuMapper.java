package com.spzx.manager.mapper;

import com.spzx.model.dto.system.AssginMenuDto;
import com.spzx.model.entity.system.SysMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * ClassName: SysRoleMenuMapper
 * Package: com.spzx.manager.mapper
 * Description:
 * Author: zhj
 * Create: 2024/4/13 - 20:53
 * Version: v1.0
 */
@Mapper
public interface SysRoleMenuMapper {
    //查询角色已经拥有的菜单
    List<Long> findSysRoleMenuByRoleId(Long roleId);

    //删除角色原有菜单
    void deleteMenu(Long roleId);

    //保存角色菜单
    void doAssign(AssginMenuDto assginMenuDto);

    //查询角色已有菜单，返回值为SysMenu的list集合
    List<SysMenu> getMenuByRoleId(List<Long> userRoleIds);
}
