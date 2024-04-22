package com.spzx.service.product.mapper;

import com.spzx.model.dto.h5.ProductSkuDto;
import com.spzx.model.entity.product.ProductSku;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * ClassName: ProductSkuMapper
 * Package: com.spzx.service.product.mapper
 * Description:
 * Author: zhj
 * Create: 2024/4/19 - 17:23
 * Version: v1.0
 */
@Mapper
public interface ProductSkuMapper {
    //分页查询
    List<ProductSku> findByPage(ProductSkuDto productSkuDto);

    //获取热销产品，按价格排序
    List<ProductSku> findSkuListBySale();

    //根据skuId查询商品单元
    ProductSku findProductSku(Long skuId);

    //根据商品id，查询同一个商品下的的sku列表
    List<ProductSku> findSkuListByPId(Long productId);
}
