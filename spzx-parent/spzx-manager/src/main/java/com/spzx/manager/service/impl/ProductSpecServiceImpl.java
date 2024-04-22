package com.spzx.manager.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.spzx.manager.mapper.ProductSpecMapper;
import com.spzx.manager.service.ProductSpecService;
import com.spzx.model.entity.product.ProductSpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName: ProductSpecServiceImpl
 * Package: com.spzx.manager.service.impl
 * Description:
 * Author: zhj
 * Create: 2024/4/14 - 18:21
 * Version: v1.0
 */
@Service
public class ProductSpecServiceImpl implements ProductSpecService {
    @Autowired
    ProductSpecMapper productSpecMapper;

    /**
     * 规格列表查询
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo<ProductSpec> findByPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<ProductSpec> list = productSpecMapper.findByPage();
        PageInfo<ProductSpec> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    /**
     * 新增产品规格
     * @param productSpec
     */
    @Override
    public void save(ProductSpec productSpec) {
        productSpecMapper.save(productSpec);
    }

    /**
     * 更新产品规格
     * @param productSpec
     */
    @Override
    public void updateById(ProductSpec productSpec) {
        productSpecMapper.updateById(productSpec);
    }

    /**
     * 删除产品规格
     * @param id
     */
    @Override
    public void removeById(Long id) {
        productSpecMapper.removeById(id);
    }

    /**
     * 查询所有商品规格
     * @return
     */
    @Override
    public List<ProductSpec> findAll() {
        return productSpecMapper.findAll();
    }
}
