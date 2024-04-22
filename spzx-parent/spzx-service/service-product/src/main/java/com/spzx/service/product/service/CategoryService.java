package com.spzx.service.product.service;

import com.spzx.model.entity.product.Category;

import java.util.List;

/**
 * ClassName: CategoryService
 * Package: com.spzx.service.product.service
 * Description:
 * Author: zhj
 * Create: 2024/4/19 - 9:11
 * Version: v1.0
 */
public interface CategoryService {
    List<Category> findListOne();

    List<Category> findCategoryTree();
}
