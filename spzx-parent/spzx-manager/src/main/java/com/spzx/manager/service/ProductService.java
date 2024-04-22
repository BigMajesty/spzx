package com.spzx.manager.service;

import com.github.pagehelper.PageInfo;
import com.spzx.model.dto.product.ProductDto;
import com.spzx.model.entity.product.Product;

/**
 * ClassName: ProductService
 * Package: com.spzx.manager.service
 * Description:
 * Author: zhj
 * Create: 2024/4/14 - 19:07
 * Version: v1.0
 */
public interface ProductService {
    PageInfo<Product> findByPage(Integer page, Integer limit,ProductDto productDto);

    void save(Product product);

    Product getById(Long id);

    void updateById(Product product);

    void deleteById(Long id);

    void updateAuditStatus(Long id, Integer auditStatus);

    void updateStatus(Long id, Integer status);
}
