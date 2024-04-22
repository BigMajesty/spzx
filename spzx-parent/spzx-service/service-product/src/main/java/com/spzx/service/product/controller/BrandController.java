package com.spzx.service.product.controller;

import com.spzx.model.entity.product.Brand;
import com.spzx.model.vo.common.Result;
import com.spzx.model.vo.common.ResultCodeEnum;
import com.spzx.service.product.service.BrandService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * ClassName: BrandController
 * Package: com.spzx.service.product.controller
 * Description:
 * Author: zhj
 * Create: 2024/4/19 - 15:38
 * Version: v1.0
 */
@Tag(name = "品牌管理")
@RestController
@RequestMapping(value="/api/product/brand")
public class BrandController {
    @Resource
    BrandService brandService;

    /**
     * 获取所有品牌
     * @return
     */
    @GetMapping("/findAll")
    public Result findAll(){
        List<Brand> list = brandService.findAll();
        return Result.build(list, ResultCodeEnum.SUCCESS);
    }
}
