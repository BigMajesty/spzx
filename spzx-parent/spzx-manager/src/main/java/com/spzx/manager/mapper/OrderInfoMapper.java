package com.spzx.manager.mapper;

import com.spzx.model.dto.order.OrderStatisticsDto;
import com.spzx.model.entity.order.OrderStatistics;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * ClassName: orderInfoMapper
 * Package: com.spzx.manager.mapper
 * Description:
 * Author: zhj
 * Create: 2024/4/15 - 11:49
 * Version: v1.0
 */
@Mapper
public interface OrderInfoMapper {
    //统计前一天的交易金额
    OrderStatistics selectOrderStatistics(String yeseterday);


}
