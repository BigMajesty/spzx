package com.spzx.manager.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.spzx.manager.mapper.CategoryBrandMapper;
import com.spzx.manager.service.CategoryBrandService;
import com.spzx.manager.service.CategoryService;
import com.spzx.model.dto.product.CategoryBrandDto;
import com.spzx.model.entity.product.Brand;
import com.spzx.model.entity.product.CategoryBrand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName: CategoryBrandServiceImpl
 * Package: com.spzx.manager.service.impl
 * Description:
 * Author: zhj
 * Create: 2024/4/14 - 16:50
 * Version: v1.0
 */
@Service
public class CategoryBrandServiceImpl implements CategoryBrandService {
    @Autowired
    CategoryBrandMapper categoryBrandMapper;

    /**
     * 查询分类品牌列表
     * @param pageNum
     * @param pageSize
     * @param categoryBrandDto
     * @return
     */
    @Override
    public PageInfo<CategoryBrand> findByPage(Integer pageNum, Integer pageSize, CategoryBrandDto categoryBrandDto) {
        PageHelper.startPage(pageNum,pageSize);
        List<CategoryBrand> list = categoryBrandMapper.findByPage(categoryBrandDto);
        PageInfo<CategoryBrand> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    /**
     * 新增分类品牌
     * @param categoryBrand
     */
    @Override
    public void save(CategoryBrand categoryBrand) {
        categoryBrandMapper.save(categoryBrand);
    }

    /**
     * 修改分类品牌
     * @param categoryBrand
     */
    @Override
    public void updateById(CategoryBrand categoryBrand) {
        categoryBrandMapper.updateById(categoryBrand);
    }

    /**
     * 删除分类品牌
     * @param id
     */
    @Override
    public void deleteById(Long id) {
        categoryBrandMapper.deleteById(id);
    }

    /**
     * 根据分类查询品牌
     * @param categoryId
     * @return
     */
    @Override
    public List<Brand> findBrandByCategoryId(Long categoryId) {
        return categoryBrandMapper.findBrandByCategoryId(categoryId);
    }
}
