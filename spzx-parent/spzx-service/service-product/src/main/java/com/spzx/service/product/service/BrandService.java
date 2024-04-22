package com.spzx.service.product.service;

import com.spzx.model.entity.product.Brand;

import java.util.List;

/**
 * ClassName: BrandService
 * Package: com.spzx.service.product.service
 * Description:
 * Author: zhj
 * Create: 2024/4/19 - 15:39
 * Version: v1.0
 */
public interface BrandService {
    List<Brand> findAll();
}
