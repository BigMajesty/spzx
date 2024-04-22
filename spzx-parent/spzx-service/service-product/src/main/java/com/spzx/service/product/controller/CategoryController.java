package com.spzx.service.product.controller;

import com.spzx.model.entity.product.Category;
import com.spzx.model.vo.common.Result;
import com.spzx.model.vo.common.ResultCodeEnum;
import com.spzx.service.product.service.CategoryService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * ClassName: CategoryController
 * Package: com.spzx.service.product.controller
 * Description:
 * Author: zhj
 * Create: 2024/4/19 - 10:42
 * Version: v1.0
 */
@RestController
@RequestMapping(value="/api/product/category")
public class CategoryController {
    @Resource
    CategoryService categoryService;

    /**
     * 获取所有分类
     * @return
     */
    @GetMapping("/findCategoryTree")
    public Result getAllCategory(){
        List<Category> list = categoryService.findCategoryTree();
        return Result.build(list, ResultCodeEnum.SUCCESS);
    }
}
