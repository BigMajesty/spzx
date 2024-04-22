package com.spzx.manager.mapper;

import com.spzx.model.dto.product.CategoryBrandDto;
import com.spzx.model.entity.product.Brand;
import com.spzx.model.entity.product.CategoryBrand;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * ClassName: CategoryBrandMapper
 * Package: com.spzx.manager.mapper
 * Description:
 * Author: zhj
 * Create: 2024/4/14 - 16:51
 * Version: v1.0
 */
@Mapper
public interface CategoryBrandMapper {

    //查询分类品牌列表
    List<CategoryBrand> findByPage(CategoryBrandDto categoryBrandDto);

    //新增分类品牌
    void save(CategoryBrand categoryBrand);

    //修改分类品牌
    void updateById(CategoryBrand categoryBrand);

    //删除分类品牌
    void deleteById(Long id);

    //根据分类查询品牌
    List<Brand> findBrandByCategoryId(Long categoryId);
}
