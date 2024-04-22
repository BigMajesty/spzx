package com.spzx.manager.service;

import com.spzx.model.entity.system.SysMenu;
import com.spzx.model.vo.system.SysMenuVo;

import java.util.List;

/**
 * ClassName: SysMenuService
 * Package: com.spzx.manager.service
 * Description:
 * Author: zhj
 * Create: 2024/4/13 - 19:31
 * Version: v1.0
 */
public interface SysMenuService {
    List<SysMenu> findNodes();

    void save(SysMenu sysMenu);

    void updateById(SysMenu sysMenu);

    void removeById(Long id);

    List<SysMenuVo> findUserMenuList();
}
