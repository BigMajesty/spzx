package com.spzx.manager.controller;

import com.github.pagehelper.PageInfo;
import com.spzx.manager.service.BrandService;
import com.spzx.model.entity.product.Brand;
import com.spzx.model.vo.common.Result;
import com.spzx.model.vo.common.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ClassName: BrandController
 * Package: com.spzx.manager.controller
 * Description:
 * Author: zhj
 * Create: 2024/4/14 - 15:32
 * Version: v1.0
 */
@RestController
@RequestMapping(value="/admin/product/brand")
public class BrandController {
    @Autowired
    BrandService brandService;

    /**
     * 查询品牌列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/{pageNum}/{pageSize}")
    public Result findBrandPage(@PathVariable(name = "pageNum")Integer pageNum,
                                @PathVariable(name = "pageSize")Integer pageSize){
        PageInfo<Brand> pageInfo = brandService.findBrandPage(pageNum,pageSize);
        return Result.build(pageInfo, ResultCodeEnum.SUCCESS);

    }

    /**
     * 查询品牌列表，返回list集合
     * @return
     */
    @GetMapping("/findAll")
    public Result findAll() {
        List<Brand> list = brandService.findAll();
        return Result.build(list,ResultCodeEnum.SUCCESS);
    }

    /**
     * 添加品牌
     * @param brand
     * @return
     */
    @PostMapping("save")
    public Result save(@RequestBody Brand brand) {
        brandService.save(brand);
        return Result.build(null,ResultCodeEnum.SUCCESS);
    }

    /**
     * 修改品牌信息
     * @param brand
     * @return
     */
    @PutMapping("updateById")
    public Result updateById(@RequestBody Brand brand) {
        brandService.updateById(brand);
        return Result.build(null,ResultCodeEnum.SUCCESS);
    }

    /**
     * 删除品牌信息
     * @param id
     * @return
     */
    @DeleteMapping("/deleteById/{id}")
    public Result deleteById(@PathVariable Long id) {
        brandService.deleteById(id);
        return Result.build(null,ResultCodeEnum.SUCCESS);
    }
}
