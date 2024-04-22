package com.spzx.manager.controller;

import com.spzx.manager.service.SysRoleMenuService;
import com.spzx.model.dto.system.AssginMenuDto;
import com.spzx.model.vo.common.Result;
import com.spzx.model.vo.common.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * ClassName: SysRoleMenuController
 * Package: com.spzx.manager.controller
 * Description:
 * Author: zhj
 * Create: 2024/4/13 - 20:52
 * Version: v1.0
 */
@RestController
@RequestMapping(value = "/admin/system/sysRoleMenu")
public class SysRoleMenuController {

    @Autowired
    SysRoleMenuService sysRoleMenuService;

    /**
     * 查询菜单列表以及拥有菜单回显
     * @param roleId
     * @return
     */
    @GetMapping(value = "/findSysRoleMenuByRoleId/{roleId}")
    public Result findSysRoleMenuByRoleId(@PathVariable(name = "roleId") Long roleId){
        //查询菜单列表以及已经拥有的菜单
        Map<String,Object> map = sysRoleMenuService.findSysRoleMenuByRoleId(roleId);
        return Result.build(map, ResultCodeEnum.SUCCESS);
    }

    /**
     * 分配菜单
     * @param assginMenuDto
     * @return
     */
    @PostMapping("/doAssign")
    public Result doAssign(@RequestBody AssginMenuDto assginMenuDto) {
        sysRoleMenuService.doAssign(assginMenuDto);
        return Result.build(null,ResultCodeEnum.SUCCESS);
    }

}
