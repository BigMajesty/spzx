package com.spzx.manager.mapper;

import com.spzx.model.entity.product.ProductSku;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * ClassName: ProducSkuMapper
 * Package: com.spzx.manager.mapper
 * Description:
 * Author: zhj
 * Create: 2024/4/14 - 19:47
 * Version: v1.0
 */
@Mapper
public interface ProductSkuMapper {
    //保存商品
    void save(ProductSku productSku);

    //查询商品SKu
    List<ProductSku> findProductSkuById(Long productId);
    //修改商品的sku数据
    void updateById(ProductSku productSku);
    //删除
    void deleteByProductId(Long id);
}
