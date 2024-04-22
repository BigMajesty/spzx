package com.spzx.manager.service;

import com.github.pagehelper.PageInfo;
import com.spzx.model.entity.product.ProductSpec;

import java.util.List;

/**
 * ClassName: ProductSpecService
 * Package: com.spzx.manager.service
 * Description:
 * Author: zhj
 * Create: 2024/4/14 - 18:21
 * Version: v1.0
 */
public interface ProductSpecService {
    PageInfo<ProductSpec> findByPage(Integer pageNum, Integer pageSize);

    void save(ProductSpec productSpec);

    void updateById(ProductSpec productSpec);

    void removeById(Long id);

    List<ProductSpec> findAll();
}
