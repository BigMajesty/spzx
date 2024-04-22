package com.spzx.manager.mapper;

import com.spzx.model.dto.product.ProductDto;
import com.spzx.model.entity.product.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * ClassName: ProductMapper
 * Package: com.spzx.manager.mapper
 * Description:
 * Author: zhj
 * Create: 2024/4/14 - 19:06
 * Version: v1.0
 */
@Mapper
public interface ProductMapper {
    //查询商品列表
    List<Product> findByPage(ProductDto productDto);

    //增加商品
    void save(Product product);

    //根据商品id查询商品基本信息
    Product findProductById(Long id);

    //修改商品基本数据
    void updateById(Product product);
    //删除
    void deleteById(Long id);
}
