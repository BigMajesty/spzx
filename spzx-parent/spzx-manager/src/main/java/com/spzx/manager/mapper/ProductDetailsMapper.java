package com.spzx.manager.mapper;

import com.spzx.model.entity.product.ProductDetails;
import org.apache.ibatis.annotations.Mapper;

/**
 * ClassName: ProducSkuMapper
 * Package: com.spzx.manager.mapper
 * Description:
 * Author: zhj
 * Create: 2024/4/14 - 19:47
 * Version: v1.0
 */
@Mapper
public interface ProductDetailsMapper {
    //保存商品详情
    void save(ProductDetails productDetails);

    //查询商品详情
    ProductDetails findProductDetailsById(Long productId);
    //修改商品详情
    void updateById(ProductDetails productDetails);
    //删除
    void deleteByProductId(Long id);
}
