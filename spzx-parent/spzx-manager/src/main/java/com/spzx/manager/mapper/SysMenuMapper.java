package com.spzx.manager.mapper;

import com.spzx.model.entity.system.SysMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * ClassName: SysMenuMapper
 * Package: com.spzx.manager.mapper
 * Description:
 * Author: zhj
 * Create: 2024/4/13 - 19:32
 * Version: v1.0
 */
@Mapper
public interface SysMenuMapper {
    //菜单列表
    List<SysMenu> findNodes();

    //增加菜单
    void addMenu(SysMenu sysMenu);

    //更新菜单
    void updateById(SysMenu sysMenu);

    //删除菜单
    void removeById(Long id);

    //查询当前菜单是否有子菜单
    int countByParentId(Long id);
}
