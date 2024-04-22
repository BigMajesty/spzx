package com.spzx.manager.service;

import com.spzx.model.dto.order.OrderStatisticsDto;
import com.spzx.model.entity.order.OrderStatistics;
import com.spzx.model.vo.order.OrderStatisticsVo;

/**
 * ClassName: OrderInfoService
 * Package: com.spzx.manager.service
 * Description:
 * Author: zhj
 * Create: 2024/4/15 - 12:31
 * Version: v1.0
 */
public interface OrderInfoService {
    OrderStatisticsVo getOrderStatisticsData(OrderStatisticsDto orderStatisticsDto);
}
