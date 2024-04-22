package com.spzx.service.product.mapper;

import com.spzx.model.entity.product.Brand;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * ClassName: BrandMapper
 * Package: com.spzx.service.product.mapper
 * Description:
 * Author: zhj
 * Create: 2024/4/19 - 15:40
 * Version: v1.0
 */
@Mapper
public interface BrandMapper {
    //获取所有品牌
    public List<Brand> findAll();
}
