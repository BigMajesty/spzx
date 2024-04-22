package com.spzx.manager.controller;

import com.spzx.common.exception.GlobalException;
import com.spzx.manager.service.SysMenuService;
import com.spzx.model.entity.system.SysMenu;
import com.spzx.model.vo.common.Result;
import com.spzx.model.vo.common.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ClassName: SysMenuController
 * Package: com.spzx.manager.controller
 * Description:
 * Author: zhj
 * Create: 2024/4/13 - 19:30
 * Version: v1.0
 */
@RestController
@RequestMapping(value="/admin/system/sysMenu")
public class SysMenuController {
    @Autowired
    SysMenuService sysMenuService;

    /**
     * 获取菜单列表
     * @return
     */
    @GetMapping("/findNodes")
    public Result<List<SysMenu>> findNodes() {
        List<SysMenu> list = sysMenuService.findNodes();
        return Result.build(list, ResultCodeEnum.SUCCESS);
    }

    /**
     * 添加菜单
     * @param sysMenu
     * @return
     */
    @PostMapping("/save")
    public Result save(@RequestBody SysMenu sysMenu) {
        sysMenuService.save(sysMenu);
        return Result.build(null,ResultCodeEnum.SUCCESS);
    }

    /**
     * 更新菜单
     * @param sysMenu
     * @return
     */
    @PutMapping("/updateById")
    public Result updateById(@RequestBody SysMenu sysMenu) {
        sysMenuService.updateById(sysMenu);
        return Result.build(null,ResultCodeEnum.SUCCESS);
    }

    /**
     * 删除菜单，逻辑删除
     * @param id
     * @return
     */
    @DeleteMapping("/removeById/{id}")
    public Result removeById(@PathVariable Long id) {
        //无子菜单进行删除：逻辑删除
        sysMenuService.removeById(id);
        return Result.build(null,ResultCodeEnum.SUCCESS);
    }
}
