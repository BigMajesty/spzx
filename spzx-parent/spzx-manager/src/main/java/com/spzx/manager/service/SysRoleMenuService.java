package com.spzx.manager.service;

import com.spzx.model.dto.system.AssginMenuDto;

import java.util.Map;

/**
 * ClassName: SysRoleMenuService
 * Package: com.spzx.manager.service
 * Description:
 * Author: zhj
 * Create: 2024/4/13 - 20:53
 * Version: v1.0
 */
public interface SysRoleMenuService {
    Map<String, Object> findSysRoleMenuByRoleId(Long roleId);

    void doAssign(AssginMenuDto assginMenuDto);
}
