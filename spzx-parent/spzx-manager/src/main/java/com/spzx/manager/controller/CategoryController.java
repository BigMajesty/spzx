package com.spzx.manager.controller;

import com.spzx.manager.service.CategoryService;
import com.spzx.model.entity.product.Category;
import com.spzx.model.vo.common.Result;
import com.spzx.model.vo.common.ResultCodeEnum;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * ClassName: CategoryController
 * Package: com.spzx.manager.controller
 * Description:
 * Author: zhj
 * Create: 2024/4/14 - 10:16
 * Version: v1.0
 */
@RestController
@RequestMapping(value="/admin/product/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    /**
     * 分类列表查询
     * @param parentId
     * @return
     */
    @Operation(summary = "根据parentId获取下级节点")
    @GetMapping(value = "/findByParentId/{parentId}")
    public Result findByParentId(@PathVariable(name = "parentId")Long parentId){
        List<Category> list = categoryService.findCategoryList(parentId);
        return Result.build(list, ResultCodeEnum.SUCCESS);
    }

    /**
     * 分类列表数据导出
     * @return
     */
    @GetMapping(value = "/exportData")
    public void exportData(HttpServletResponse httpServletResponse){
        categoryService.exportData(httpServletResponse);
    }

    /**
     * 分类数据导入
     * @param file
     * @return
     */
    @PostMapping("/importData")
    public Result importData(MultipartFile file){
        categoryService.importData(file);
        return Result.build(null,ResultCodeEnum.SUCCESS);
    }


}
