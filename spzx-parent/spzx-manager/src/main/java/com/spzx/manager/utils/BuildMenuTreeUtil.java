package com.spzx.manager.utils;

import com.spzx.model.entity.system.SysMenu;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: BuildMenuTreeUtil
 * Package: com.spzx.manager.utils
 * Description:
 * Author: zhj
 * Create: 2024/4/13 - 19:58
 * Version: v1.0
 */
//对得到的菜单数据进行层级结构生成
public class BuildMenuTreeUtil {
    public static List<SysMenu> buildTree(List<SysMenu> list,Long parentId){
        if(list==null)return null;
        List<SysMenu> treeList = new ArrayList<>();
        for(SysMenu sysMenu : list){
            if(sysMenu.getParentId().longValue() == parentId){
                treeList.add(sysMenu);
                sysMenu.setChildren(buildTree(list,sysMenu.getId()));
            }
        }
        return treeList;
    }
}
