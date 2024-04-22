package com.spzx.manager.service.impl;

import cn.hutool.core.date.DateUtil;
import com.spzx.manager.mapper.OrderInfoMapper;
import com.spzx.manager.mapper.OrderStatisticsMapper;
import com.spzx.manager.service.OrderInfoService;
import com.spzx.model.dto.order.OrderStatisticsDto;
import com.spzx.model.entity.order.OrderStatistics;
import com.spzx.model.vo.order.OrderStatisticsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ClassName: OrderInfoServiceImpl
 * Package: com.spzx.manager.service.impl
 * Description:
 * Author: zhj
 * Create: 2024/4/15 - 12:31
 * Version: v1.0
 */
@Service
public class OrderInfoServiceImpl implements OrderInfoService {
    @Autowired
    OrderInfoMapper orderInfoMapper;

    @Autowired
    OrderStatisticsMapper orderStatisticsMapper;
    /**
     * 获取统计结果集
     * @param orderStatisticsDto
     * @return
     */
    @Override
    public OrderStatisticsVo getOrderStatisticsData(OrderStatisticsDto orderStatisticsDto) {
        //查询统计结果数据，封装到list集合
        List<OrderStatistics> list = orderStatisticsMapper.selectList(orderStatisticsDto);
        //将list集合中的日期数据抽取
        List<String> dateList = list.stream().map(orderStatistics -> DateUtil.format(orderStatistics.getOrderDate(), "yyyy-MM-dd")).collect(Collectors.toList());
        //将list集合中总金额数据抽取,统计金额列表
        List<BigDecimal> amountList = list.stream().map(OrderStatistics::getTotalAmount).collect(Collectors.toList());
        //构造OrderStatisticsVo
        OrderStatisticsVo orderStatisticsVo = new OrderStatisticsVo();
        orderStatisticsVo.setDateList(dateList);
        orderStatisticsVo.setAmountList(amountList);
        return orderStatisticsVo;
    }
}
