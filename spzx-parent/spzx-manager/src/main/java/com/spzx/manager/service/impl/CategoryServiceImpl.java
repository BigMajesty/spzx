package com.spzx.manager.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.spzx.common.exception.GlobalException;
import com.spzx.manager.listener.ExcelListener;
import com.spzx.manager.mapper.CategoryMapper;
import com.spzx.manager.service.CategoryService;
import com.spzx.model.entity.product.Category;
import com.spzx.model.vo.common.ResultCodeEnum;
import com.spzx.model.vo.product.CategoryExcelVo;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * ClassName: CategoryServiceImpl
 * Package: com.spzx.manager.service.impl
 * Description:
 * Author: zhj
 * Create: 2024/4/14 - 10:16
 * Version: v1.0
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryMapper categoryMapper;

    /**
     * 分类列表查询
     * @param parentId
     * @return
     */
    @Override
    public List<Category> findCategoryList(Long parentId) {
        //根据parentId查询列表（一级列表）
        List<Category> list = categoryMapper.findCategoryList(parentId);
        //设置列表的hasChildren，有无子级
        if(!CollectionUtils.isEmpty(list)){
            for(Category item : list){
                int count = categoryMapper.countByParentId(item.getId());
                if(count>0){
                    item.setHasChildren(true);
                }
                else{
                    item.setHasChildren(false);
                }
            }
        }
        //返回列表
        return list;
    }

    /**
     * 分类列表数据导出
     * @param response
     */
    @Override
    public void exportData(HttpServletResponse response) {
        // 设置响应结果类型
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");

        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileName = null;
        try {
            fileName = URLEncoder.encode("分类数据", "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
        //response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");

        // 查询数据库中的数据
        List<Category> categoryList = categoryMapper.selectAll();
        List<CategoryExcelVo> categoryExcelVoList = new ArrayList<>(categoryList.size());

        // 将从数据库中查询到的Category对象转换成CategoryExcelVo对象
        for(Category category : categoryList) {
            CategoryExcelVo categoryExcelVo = new CategoryExcelVo();
            BeanUtils.copyProperties(category, categoryExcelVo, CategoryExcelVo.class);
            categoryExcelVoList.add(categoryExcelVo);
        }

        try {
            EasyExcel.write(response.getOutputStream(), CategoryExcelVo.class).sheet("分类数据").doWrite(categoryExcelVoList);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 分类数据导入
     * @param file
     */
    @Override
    public void importData(MultipartFile file) {
        ExcelListener<CategoryExcelVo> excelListener = new ExcelListener<>(categoryMapper);
        try {
            EasyExcel.read(file.getInputStream(), CategoryExcelVo.class,excelListener).sheet().doRead();
        } catch (IOException e) {
            e.printStackTrace();
            throw new GlobalException(ResultCodeEnum.DATA_ERROR);
        }
    }
}
