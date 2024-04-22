package com.spzx.manager.controller;

import com.github.pagehelper.PageInfo;
import com.spzx.common.log.annotation.Log;
import com.spzx.manager.service.SysRoleService;
import com.spzx.model.dto.system.SysRoleDto;
import com.spzx.model.entity.system.SysRole;
import com.spzx.model.vo.common.Result;
import com.spzx.model.vo.common.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * ClassName: SysRoleController
 * Package: com.spzx.manager.controller
 * Description:
 * Author: zhj
 * Create: 2024/4/12 - 19:05
 * Version: v1.0
 */
@RestController
@RequestMapping(value = "/admin/system/sysRole")
public class SysRoleController {
    @Autowired
    SysRoleService sysRoleService;

    /**
     * 角色列表分页查询
     * @param current
     * @param limit
     * @param sysRoleDto
     * @return
     */
    @PostMapping("/findByPage/{current}/{limit}")
    public Result findByPage(@PathVariable("current")Integer current,
                             @PathVariable("limit")Integer limit,
                             @RequestBody SysRoleDto sysRoleDto){
        //pageHelper插件实现分页
        PageInfo<SysRole> pageInfo=sysRoleService.findByPage(sysRoleDto,current,limit);
        return Result.build(pageInfo, ResultCodeEnum.SUCCESS);
    }

    /**
     * 添加角色
     * @param sysRole
     * @return
     */
    @Log(title = "角色管理：添加",businessType = 1)
    @PostMapping("/saveSysRole")
    public Result saveSysRole(@RequestBody SysRole sysRole){
        sysRoleService.saveSysRole(sysRole);
        return Result.build(null,ResultCodeEnum.SUCCESS);
    }

    /**
     * 修改角色信息
     * @param sysRole
     * @return
     */

    @PutMapping("/updateSysRole")
    public Result updateSysRole(@RequestBody SysRole sysRole){
        sysRoleService.updateSysRole(sysRole);
        return Result.build(null,ResultCodeEnum.SUCCESS);
    }

    /**
     * 删除角色：逻辑删除：is_deleted=1
     * @param roleId
     * @return
     */
    @DeleteMapping(value = "/deleteById/{roleId}")
    public Result deleteById(@PathVariable(value = "roleId") Long roleId) {
        sysRoleService.deleteById(roleId);
        return Result.build(null,ResultCodeEnum.SUCCESS);
    }

    /**
     * 查询所有角色，以及当前用户的角色
     * @return
     */
    @GetMapping(value = "/findAllRoles/{userId}")
    public Result<Map<String , Object>> findAllRoles(@PathVariable(value = "userId") Long userId) {
        Map<String,Object> map = sysRoleService.findAllRoles(userId);
        return Result.build(map,ResultCodeEnum.SUCCESS);
    }
}
