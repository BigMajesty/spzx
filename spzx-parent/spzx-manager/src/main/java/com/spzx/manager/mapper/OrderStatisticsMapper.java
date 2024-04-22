package com.spzx.manager.mapper;

import com.spzx.model.dto.order.OrderStatisticsDto;
import com.spzx.model.entity.order.OrderStatistics;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * ClassName: OrderStatisticsMapper
 * Package: com.spzx.manager.mapper
 * Description:
 * Author: zhj
 * Create: 2024/4/15 - 11:49
 * Version: v1.0
 */
@Mapper
public interface OrderStatisticsMapper {
    //将前一天的数据添加到统计结果集中
    void insert(OrderStatistics orderStatistics);

    //统计结果集
    List<OrderStatistics> selectList(OrderStatisticsDto orderStatisticsDto);
}
