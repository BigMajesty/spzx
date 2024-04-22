package com.spzx.manager.controller;

import com.github.pagehelper.PageInfo;
import com.spzx.manager.service.ProductSpecService;
import com.spzx.model.entity.product.ProductSpec;
import com.spzx.model.vo.common.Result;
import com.spzx.model.vo.common.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ClassName: ProductSpecController
 * Package: com.spzx.manager.controller
 * Description:
 * Author: zhj
 * Create: 2024/4/14 - 18:20
 * Version: v1.0
 */
@RestController
@RequestMapping(value="/admin/product/productSpec")
public class ProductSpecController {
    @Autowired
    ProductSpecService productSpecService;

    /**
     * 规格列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/{pageNum}/{pageSize}")
    public Result<PageInfo<ProductSpec>> findByPage(@PathVariable Integer pageNum, @PathVariable Integer pageSize) {
        PageInfo<ProductSpec> pageInfo = productSpecService.findByPage(pageNum,pageSize);
        return Result.build(pageInfo, ResultCodeEnum.SUCCESS);
    }

    /**
     * 新增产品规格
     * @param productSpec
     * @return
     */
    @PostMapping("save")
    public Result save(@RequestBody ProductSpec productSpec) {
        productSpecService.save(productSpec);
        return Result.build(null,ResultCodeEnum.SUCCESS);
    }

    /**
     * 更新产品规格
     * @param productSpec
     * @return
     */
    @PutMapping("updateById")
    public Result updateById(@RequestBody ProductSpec productSpec) {
        productSpecService.updateById(productSpec);
        return Result.build(null,ResultCodeEnum.SUCCESS);
    }

    /**
     * 删除产品规格
     * @param id
     * @return
     */
    @DeleteMapping("/deleteById/{id}")
    public Result removeById(@PathVariable Long id) {
        productSpecService.removeById(id);
        return Result.build(null,ResultCodeEnum.SUCCESS);
    }

    /**
     * 查询所有商品规格
     * @return
     */
    @GetMapping("findAll")
    public Result findAll() {
        List<ProductSpec> list = productSpecService.findAll();
        return Result.build(list,ResultCodeEnum.SUCCESS);
    }
}
