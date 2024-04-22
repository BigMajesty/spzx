package com.spzx.manager.mapper;

import com.spzx.model.entity.product.ProductSpec;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * ClassName: ProductSpecMapper
 * Package: com.spzx.manager.mapper
 * Description:
 * Author: zhj
 * Create: 2024/4/14 - 18:21
 * Version: v1.0
 */
@Mapper
public interface ProductSpecMapper {
    //查询规格列表
    public List<ProductSpec> findByPage();

    //新增产品规格
    void save(ProductSpec productSpec);

    //更新产品规格
    void updateById(ProductSpec productSpec);

    //删除产品规格
    void removeById(Long id);

    //查询所有商品规格
    List<ProductSpec> findAll();
}
