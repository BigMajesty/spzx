package com.spzx.manager.service.impl;

import com.spzx.manager.mapper.ProductUnitMapper;
import com.spzx.manager.service.ProductUnitService;
import com.spzx.model.entity.base.ProductUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName: ProductUnitServiceImpl
 * Package: com.spzx.manager.service.impl
 * Description:
 * Author: zhj
 * Create: 2024/4/14 - 19:27
 * Version: v1.0
 */
@Service
public class ProductUnitServiceImpl implements ProductUnitService {
    @Autowired
    ProductUnitMapper productUnitMapper;

    /**
     * 查询商品单元
     * @return
     */
    @Override
    public List<ProductUnit> findAll() {
        return productUnitMapper.findAll();
    }
}
