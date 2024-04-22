package com.spzx.manager.service;

import com.spzx.model.entity.product.Category;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * ClassName: CategoryService
 * Package: com.spzx.manager.service
 * Description:
 * Author: zhj
 * Create: 2024/4/14 - 10:16
 * Version: v1.0
 */
public interface CategoryService {
    List<Category> findCategoryList(Long parentId);

    void exportData(HttpServletResponse httpServletResponse);

    void importData(MultipartFile file);
}
