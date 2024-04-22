package com.spzx.service.product.mapper;

import com.spzx.model.entity.product.Product;
import org.apache.ibatis.annotations.Mapper;

/**
 * ClassName: ProductMapper
 * Package: com.spzx.service.product.mapper
 * Description:
 * Author: zhj
 * Create: 2024/4/19 - 17:23
 * Version: v1.0
 */
@Mapper
public interface ProductMapper {

    //根据id查询商品基本信息
    Product getProductById(Long id);
}
