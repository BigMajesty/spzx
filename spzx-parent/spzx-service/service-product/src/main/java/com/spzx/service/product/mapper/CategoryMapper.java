package com.spzx.service.product.mapper;

import com.spzx.model.entity.product.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * ClassName: CategoryMapper
 * Package: com.spzx.service.product.mapper
 * Description:
 * Author: zhj
 * Create: 2024/4/19 - 9:14
 * Version: v1.0
 */
@Mapper
public interface CategoryMapper {
    //获取一级分类商品
    List<Category> findListOne();

    //查询所有分类
    List<Category> findCategoryAll();
}
