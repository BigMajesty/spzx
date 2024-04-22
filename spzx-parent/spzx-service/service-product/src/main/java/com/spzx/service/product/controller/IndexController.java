package com.spzx.service.product.controller;

import com.spzx.model.entity.product.Category;
import com.spzx.model.entity.product.ProductSku;
import com.spzx.model.vo.common.Result;
import com.spzx.model.vo.common.ResultCodeEnum;
import com.spzx.model.vo.h5.IndexVo;
import com.spzx.service.product.service.CategoryService;
import com.spzx.service.product.service.ProductService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * ClassName: IndexController
 * Package: com.spzx.service.product.controller
 * Description:
 * Author: zhj
 * Create: 2024/4/19 - 9:09
 * Version: v1.0
 */
@Tag(name = "首页接口管理")
@RestController
@RequestMapping(value = "/api/product/index")
public class IndexController {
    @Resource
    ProductService productService;
    @Resource
    CategoryService categoryService;

    /**
     * 获取H5首页数据：一级分类列表，热销产品
     * @return
     */
    @GetMapping
    public Result getData(){
        //获取一级分类商品
        List<Category> categoryList = categoryService.findListOne();
        //获取热销产品
        List<ProductSku> productSkuList = productService.findSkuListBySale();
        IndexVo indexVo = new IndexVo();
        indexVo.setCategoryList(categoryList);
        indexVo.setProductSkuList(productSkuList);
        return Result.build(indexVo, ResultCodeEnum.SUCCESS);
    }


}
