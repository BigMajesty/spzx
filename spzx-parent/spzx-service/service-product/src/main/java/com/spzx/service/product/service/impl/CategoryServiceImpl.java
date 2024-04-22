package com.spzx.service.product.service.impl;

import com.alibaba.cloud.commons.lang.StringUtils;
import com.alibaba.fastjson.JSON;
import com.spzx.model.entity.product.Category;
import com.spzx.service.product.mapper.CategoryMapper;
import com.spzx.service.product.service.CategoryService;
import com.spzx.service.product.utils.BuildCategoryTreeUtil;
import jakarta.annotation.Resource;
import org.apache.poi.util.StringUtil;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * ClassName: CategoryServiceImpl
 * Package: com.spzx.service.product.service.impl
 * Description:
 * Author: zhj
 * Create: 2024/4/19 - 9:13
 * Version: v1.0
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    @Resource
    CategoryMapper categoryMapper;

    @Resource
    RedisTemplate<String,String> redisTemplate;

    /**
     * 获取一级分类商品
     * @return
     */
    @Override
    public List<Category> findListOne() {
        //获取redis缓存里的数据
        String categoryListOne = redisTemplate.opsForValue().get("category:one");
        if(!StringUtils.isEmpty(categoryListOne)){
//            System.out.println("从redis里查询到了一级列表");
            return  JSON.parseArray(categoryListOne, Category.class);
        }
        //如果未查询到数据，则从数据库里查询，并将数据存入到redis里
        List<Category> listOne = categoryMapper.findListOne();
        //存储到redis，七天有效期
        redisTemplate.opsForValue().setIfAbsent("category:one",JSON.toJSONString(listOne),7,TimeUnit.DAYS);
//        System.out.println("从数据库里查询到的一级列表");
        return listOne;
    }

    /**
     * 获取分类
     * @return
     */
    @Override
    @Cacheable(value = "category" ,key = "'all'")
    public List<Category> findCategoryTree() {
        List<Category> list= categoryMapper.findCategoryAll();
        List<Category> treeList = BuildCategoryTreeUtil.buildTree(list, 0L);
        return treeList;
    }
}
