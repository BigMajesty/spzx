package com.spzx.manager.mapper;

import com.spzx.model.entity.product.Category;
import com.spzx.model.vo.product.CategoryExcelVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * ClassName: CategoryMapper
 * Package: com.spzx.manager.mapper
 * Description:
 * Author: zhj
 * Create: 2024/4/14 - 10:17
 * Version: v1.0
 */
@Mapper
public interface CategoryMapper {
    //获取分类列表（一级列表）
    List<Category> findCategoryList(Long parentId);
    //计算子分类的个数
    int countByParentId(Long id);
    //查询所有分类
    List<Category> selectAll();

    //批量写入分类数据（Excel导入）
    void batchInsert(List<CategoryExcelVo> categoryList);
}
