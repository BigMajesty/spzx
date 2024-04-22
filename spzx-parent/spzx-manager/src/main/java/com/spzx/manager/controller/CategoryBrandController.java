package com.spzx.manager.controller;

import com.github.pagehelper.PageInfo;
import com.spzx.manager.service.CategoryBrandService;
import com.spzx.model.dto.product.CategoryBrandDto;
import com.spzx.model.entity.product.Brand;
import com.spzx.model.entity.product.CategoryBrand;
import com.spzx.model.entity.product.ProductSpec;
import com.spzx.model.vo.common.Result;
import com.spzx.model.vo.common.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ClassName: CategoryBrandController
 * Package: com.spzx.manager.controller
 * Description:
 * Author: zhj
 * Create: 2024/4/14 - 16:50
 * Version: v1.0
 */
@RestController
@RequestMapping(value = "/admin/product/categoryBrand")
public class CategoryBrandController {
    @Autowired
    CategoryBrandService categoryBrandService;

    /**
     * 查询分类品牌列表
     * @param pageNum
     * @param pageSize
     * @param CategoryBrandDto
     * @return
     */
    @GetMapping("/{pageNum}/{pageSize}")
    public Result<PageInfo<CategoryBrand>> findByPage(@PathVariable Integer pageNum,
                                                      @PathVariable Integer pageSize,
                                                      CategoryBrandDto CategoryBrandDto){
        PageInfo<CategoryBrand> pageInfo = categoryBrandService.findByPage(pageNum,pageSize,CategoryBrandDto);
        return Result.build(pageInfo, ResultCodeEnum.SUCCESS);
    }

    /**
     * 新增分类品牌
     * @param categoryBrand
     * @return
     */
    @PostMapping("/save")
    public Result save(@RequestBody CategoryBrand categoryBrand) {
        categoryBrandService.save(categoryBrand);
        return Result.build(null,ResultCodeEnum.SUCCESS);
    }

    /**
     * 修改分类品牌
     * @param categoryBrand
     * @return
     */
    @PutMapping("updateById")
    public Result updateById(@RequestBody CategoryBrand categoryBrand) {
        categoryBrandService.updateById(categoryBrand);
        return Result.build(null,ResultCodeEnum.SUCCESS);
    }

    /**
     * 删除分类品牌
     * @param id
     * @return
     */
    @DeleteMapping("/deleteById/{id}")
    public Result deleteById(@PathVariable Long id) {
        categoryBrandService.deleteById(id);
        return Result.build(null,ResultCodeEnum.SUCCESS);
    }

    //根据分类查询品牌
    @GetMapping("/findBrandByCategoryId/{categoryId}")
    public Result findBrandByCategoryId(@PathVariable Long categoryId) {
        List<Brand> list = categoryBrandService.findBrandByCategoryId(categoryId);
        return Result.build(list,ResultCodeEnum.SUCCESS);
    }

}