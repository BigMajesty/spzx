package com.spzx.service.product.service;

import com.github.pagehelper.PageInfo;
import com.spzx.model.dto.h5.ProductSkuDto;
import com.spzx.model.entity.product.ProductSku;
import com.spzx.model.vo.h5.ProductItemVo;

import java.util.List;

/**
 * ClassName: ProductService
 * Package: com.spzx.service.product.service
 * Description:
 * Author: zhj
 * Create: 2024/4/19 - 9:12
 * Version: v1.0
 */
public interface ProductService {
    List<ProductSku> findSkuListBySale();

    PageInfo<ProductSku> findByPage(Integer page, Integer limit, ProductSkuDto productSkuDto);

    ProductItemVo getProductItemBySkuId(Long skuId);

    ProductSku getBySkuId(Long skuId);
}
