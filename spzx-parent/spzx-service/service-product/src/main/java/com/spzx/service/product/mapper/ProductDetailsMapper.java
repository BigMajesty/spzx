package com.spzx.service.product.mapper;

import com.spzx.model.entity.product.ProductDetails;
import org.apache.ibatis.annotations.Mapper;

/**
 * ClassName: ProductDetails
 * Package: com.spzx.service.product.mapper
 * Description:
 * Author: zhj
 * Create: 2024/4/19 - 17:42
 * Version: v1.0
 */
@Mapper
public interface ProductDetailsMapper {
    //根据商品id，查询商品详情
    ProductDetails findProductDetailsById(Long parentId);
}
