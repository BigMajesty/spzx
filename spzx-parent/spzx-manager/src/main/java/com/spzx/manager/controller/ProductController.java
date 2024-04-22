package com.spzx.manager.controller;

import com.github.pagehelper.PageInfo;
import com.spzx.manager.service.ProductService;
import com.spzx.model.dto.product.ProductDto;
import com.spzx.model.entity.product.Product;
import com.spzx.model.entity.product.ProductSku;
import com.spzx.model.vo.common.Result;
import com.spzx.model.vo.common.ResultCodeEnum;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * ClassName: ProductController
 * Package: com.spzx.manager.controller
 * Description:
 * Author: zhj
 * Create: 2024/4/14 - 19:06
 * Version: v1.0
 */
@RestController
@RequestMapping(value="/admin/product/product")
public class ProductController {
    @Autowired
    ProductService productService;

    /**
     * 查询商品列表
     * @param page
     * @param limit
     * @param productDto
     * @return
     */
    @GetMapping(value = "/{page}/{limit}")
    public Result findByPage(@PathVariable Integer page,
                                                @PathVariable Integer limit,
                                                ProductDto productDto) {
        PageInfo<Product> pageInfo = productService.findByPage(page,limit,productDto);
        return Result.build(pageInfo, ResultCodeEnum.SUCCESS);
    }

    /**
     * 添加商品
     * @param product
     * @return
     */
    @PostMapping("/save")
    public Result save(@RequestBody Product product){
        productService.save(product);
        return Result.build(null,ResultCodeEnum.SUCCESS);
    }

    /**
     * 回显商品信息
     * @param id
     * @return
     */
    @GetMapping("/getById/{id}")
    public Result getById(@PathVariable Long id){
        Product product = productService.getById(id);
        return Result.build(product,ResultCodeEnum.SUCCESS);
    }

    /**
     * 修改商品信息
     * @param product
     * @return
     */
    @PutMapping("/updateById")
    public Result updateById(@Parameter(name = "product", description = "请求参数实体类", required = true) @RequestBody Product product) {
        productService.updateById(product);
        return Result.build(null , ResultCodeEnum.SUCCESS) ;
    }

    /**
     * 删除商品信息
     * @param id
     * @return
     */
    @DeleteMapping("/deleteById/{id}")
    public Result deleteById(@Parameter(name = "id", description = "商品id", required = true) @PathVariable Long id) {
        productService.deleteById(id);
        return Result.build(null , ResultCodeEnum.SUCCESS) ;
    }

    /**
     * 商品审核
     * @param id
     * @param auditStatus
     * @return
     */
    @GetMapping("/updateAuditStatus/{id}/{auditStatus}")
    public Result updateAuditStatus(@PathVariable Long id, @PathVariable Integer auditStatus) {
        productService.updateAuditStatus(id,auditStatus);
        return Result.build(null,ResultCodeEnum.SUCCESS);
    }

    /**
     * 商品上下架
     * @param id
     * @param status
     * @return
     */
    @GetMapping("/updateStatus/{id}/{status}")
    public Result updateStatus(@PathVariable Long id, @PathVariable Integer status) {
        productService.updateStatus(id, status);
        return Result.build(null , ResultCodeEnum.SUCCESS) ;
    }

}
