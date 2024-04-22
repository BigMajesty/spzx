package com.spzx.manager.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.spzx.manager.mapper.BrandMapper;
import com.spzx.manager.service.BrandService;
import com.spzx.model.entity.product.Brand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName: BrandServiceImpl
 * Package: com.spzx.manager.service.impl
 * Description:
 * Author: zhj
 * Create: 2024/4/14 - 15:43
 * Version: v1.0
 */
@Service
public class BrandServiceImpl implements BrandService {
    @Autowired
    BrandMapper brandMapper;


    /**
     * 查询品牌列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo<Brand> findBrandPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Brand> list = brandMapper.findBrandPage();
        PageInfo<Brand> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    /**
     * 添加品牌
     * @param brand
     */
    @Override
    public void save(Brand brand) {
        brandMapper.save(brand);
    }

    /**
     * 修改品牌
     * @param brand
     */
    @Override
    public void updateById(Brand brand) {
        brandMapper.updateById(brand);
    }

    /**
     * 删除品牌
     * @param id
     */
    @Override
    public void deleteById(Long id) {
        brandMapper.deleteById(id);
    }

    /**
     * 查询品牌列表，返回list集合
     * @return
     */
    @Override
    public List<Brand> findAll() {
        return brandMapper.findAll();
    }
}
