package com.spzx.manager.service;

import com.github.pagehelper.PageInfo;
import com.spzx.model.dto.product.CategoryBrandDto;
import com.spzx.model.entity.product.Brand;
import com.spzx.model.entity.product.CategoryBrand;

import java.util.List;

/**
 * ClassName: CategoryBrandService
 * Package: com.spzx.manager.service
 * Description:
 * Author: zhj
 * Create: 2024/4/14 - 16:50
 * Version: v1.0
 */
public interface CategoryBrandService {
    PageInfo<CategoryBrand> findByPage(Integer pageNum, Integer pageSize, CategoryBrandDto categoryBrandDto);

    void save(CategoryBrand categoryBrand);

    void updateById(CategoryBrand categoryBrand);

    void deleteById(Long id);

    List<Brand> findBrandByCategoryId(Long categoryId);
}
