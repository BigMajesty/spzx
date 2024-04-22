package com.spzx.manager.controller;

import com.spzx.manager.service.ProductUnitService;
import com.spzx.model.entity.base.ProductUnit;
import com.spzx.model.vo.common.Result;
import com.spzx.model.vo.common.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * ClassName: ProductUnitController
 * Package: com.spzx.manager.controller
 * Description:
 * Author: zhj
 * Create: 2024/4/14 - 19:27
 * Version: v1.0
 */
@RestController
@RequestMapping("/admin/product/productUnit")
public class ProductUnitController {
    @Autowired
    private ProductUnitService productUnitService;

    /**
     * 查询商品单元列表
     * @return
     */
    @GetMapping("findAll")
    public Result findAll() {
        List<ProductUnit> list =productUnitService.findAll();
        return Result.build(list, ResultCodeEnum.SUCCESS);
    }
}
