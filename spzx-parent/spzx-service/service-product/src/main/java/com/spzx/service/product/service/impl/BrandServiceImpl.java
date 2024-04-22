package com.spzx.service.product.service.impl;

import com.spzx.model.entity.product.Brand;
import com.spzx.service.product.mapper.BrandMapper;
import com.spzx.service.product.service.BrandService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName: BrandServiceImpl
 * Package: com.spzx.service.product.service.impl
 * Description:
 * Author: zhj
 * Create: 2024/4/19 - 15:39
 * Version: v1.0
 */
@Service
public class BrandServiceImpl implements BrandService {
    @Resource
    BrandMapper brandMapper;

    /**
     * 获取所有品牌
     * @return
     */
    @Override
    public List<Brand> findAll() {
        return  brandMapper.findAll();
    }
}
