package com.spzx.manager.service;

import com.github.pagehelper.PageInfo;
import com.spzx.model.entity.product.Brand;

import java.util.List;

/**
 * ClassName: BrandService
 * Package: com.spzx.manager.service
 * Description:
 * Author: zhj
 * Create: 2024/4/14 - 15:43
 * Version: v1.0
 */
public interface BrandService {
    PageInfo<Brand> findBrandPage(Integer pageNum, Integer pageSize);

    void save(Brand brand);

    void updateById(Brand brand);

    void deleteById(Long id);

    List<Brand> findAll();
}
