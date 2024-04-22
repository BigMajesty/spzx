package com.spzx.manager.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.util.ListUtils;
import com.spzx.manager.mapper.CategoryMapper;
import com.spzx.model.vo.product.CategoryExcelVo;
import org.apache.poi.ss.formula.functions.T;

import java.util.List;

/**
 * ClassName: ExcelListener
 * Package: com.spzx.manager.listener
 * Description:
 * Author: zhj
 * Create: 2024/4/14 - 11:18
 * Version: v1.0
 */
//不能交给Spring管理,因为它是单实例，并发情况下会出问题
public class ExcelListener<T> implements ReadListener<T> {

    //构造传递mapper，操作数据库
    private CategoryMapper categoryMapper;
    public ExcelListener(CategoryMapper categoryMapper){
        this.categoryMapper = categoryMapper;
    }

    /**
     每隔5条存储数据库，实际使用中可以100条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 100;
    /**
     * 缓存的数据
     */
    private List<T> cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);

    //从第二行开始读取，把每行读取内容封装到t对象里面
    @Override
    public void invoke(T data, AnalysisContext context) {
        cachedDataList.add(data);
        if(cachedDataList.size()>BATCH_COUNT){
            saveData();
            cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
        }
    }
    //读取完毕之后执行的操作
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        // excel解析完毕以后需要执行的代码
        // 这里也要保存数据，确保最后遗留的数据也存储到数据库
        saveData();
    }

    //往数据库写数据
    private void saveData() {
        categoryMapper.batchInsert((List<CategoryExcelVo>)cachedDataList);
    }
}
