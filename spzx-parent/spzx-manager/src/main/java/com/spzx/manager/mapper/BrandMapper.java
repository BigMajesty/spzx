package com.spzx.manager.mapper;

import com.spzx.model.entity.product.Brand;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * ClassName: BrandMapper
 * Package: com.spzx.manager.mapper
 * Description:
 * Author: zhj
 * Create: 2024/4/14 - 15:43
 * Version: v1.0
 */
@Mapper
public interface BrandMapper {

    //查询品牌列表
    List<Brand> findBrandPage();

    //添加品牌
    void save(Brand brand);

    //修改品牌
    void updateById(Brand brand);

    //删除品牌
    void deleteById(Long id);

    //品牌列表，list
    List<Brand> findAll();
}
