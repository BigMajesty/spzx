package com.spzx.service.product.utils;

import com.spzx.model.entity.product.Category;
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
public class BuildCategoryTreeUtil {
    public static List<Category> buildTree(List<Category> list, Long parentId){
        if(list==null)return null;
        List<Category> treeList = new ArrayList<>();
        for(Category category : list){
            if(category.getParentId().longValue() == parentId){
                treeList.add(category);
                category.setChildren(buildTree(list,category.getId()));
            }
        }
        return treeList;
    }
}
