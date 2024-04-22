package com.spzx.manager.mapper;

import com.spzx.model.entity.base.ProductUnit;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * ClassName: ProductUnitMapper
 * Package: com.spzx.manager.mapper
 * Description:
 * Author: zhj
 * Create: 2024/4/14 - 19:27
 * Version: v1.0
 */
@Mapper
public interface ProductUnitMapper {
    //查询商品单元
    List<ProductUnit> findAll();
}
