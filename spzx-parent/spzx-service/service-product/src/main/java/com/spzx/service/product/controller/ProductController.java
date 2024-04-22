package com.spzx.service.product.controller;

import com.github.pagehelper.PageInfo;
import com.spzx.model.dto.h5.ProductSkuDto;
import com.spzx.model.entity.product.ProductSku;
import com.spzx.model.vo.common.Result;
import com.spzx.model.vo.common.ResultCodeEnum;
import com.spzx.model.vo.h5.ProductItemVo;
import com.spzx.service.product.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: ProductController
 * Package: com.spzx.service.product.controller
 * Description:
 * Author: zhj
 * Create: 2024/4/19 - 16:01
 * Version: v1.0
 */

@Tag(name = "商品列表管理")
@RestController
@RequestMapping(value="/api/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    /**
     * 分页查询产品列表
     * @param page
     * @param limit
     * @param productSkuDto
     * @return
     */
    @Operation(summary = "分页查询")
    @GetMapping(value = "/{page}/{limit}")
    public Result<PageInfo<ProductSku>> findByPage(@PathVariable Integer page,
                                                   @PathVariable Integer limit,
                                                   ProductSkuDto productSkuDto){
        PageInfo<ProductSku> pageInfo = productService.findByPage(page,limit,productSkuDto);
        return Result.build(pageInfo, ResultCodeEnum.SUCCESS);
    }


    /**
     * 查询商品详情
     * @param skuId
     * @return
     */
    @GetMapping("/item/{skuId}")
    public Result getProductItemBySkuId(@PathVariable(name = "skuId")Long skuId){
        ProductItemVo productItemVo = productService.getProductItemBySkuId(skuId);
        return Result.build(productItemVo,ResultCodeEnum.SUCCESS);
    }

    /**
     * 根据id获取商品sku信息
     * @param skuId
     * @return
     */
    @Operation(summary = "获取商品sku信息")
    @GetMapping("getBySkuId/{skuId}")
    public ProductSku getBySkuId(@PathVariable(name = "skuId") Long skuId){
        ProductSku productSku = productService.getBySkuId(skuId);
        return productSku;
    }
}