package com.spzx.manager.service;

import com.spzx.model.entity.base.ProductUnit;

import java.util.List;

/**
 * ClassName: ProductUnitService
 * Package: com.spzx.manager.service
 * Description:
 * Author: zhj
 * Create: 2024/4/14 - 19:27
 * Version: v1.0
 */
public interface ProductUnitService {
    List<ProductUnit> findAll();
}
