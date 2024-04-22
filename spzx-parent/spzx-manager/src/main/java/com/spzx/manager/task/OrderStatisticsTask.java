package com.spzx.manager.task;

import cn.hutool.core.date.DateUtil;
import com.spzx.manager.mapper.OrderInfoMapper;
import com.spzx.manager.mapper.OrderStatisticsMapper;
import com.spzx.model.entity.order.OrderStatistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * ClassName: OrderStatisticsTask
 * Package: com.spzx.manager.task
 * Description:
 * Author: zhj
 * Create: 2024/4/15 - 11:43
 * Version: v1.0
 */
@Component
public class OrderStatisticsTask {
    @Autowired
    OrderInfoMapper orderInfoMapper;
    @Autowired
    OrderStatisticsMapper orderStatisticsMapper;

    //每天凌晨两点，查询前一天统计数据，把统计之后数据添加统计结果表里面
    @Scheduled(cron = "0 0 2 * * ?")
    public void orderTotalAmountStatistics(){
        //获取前一天日期
        String yeseterday = DateUtil.offsetDay(new Date(), -1).toString("yyyy-MM-dd");
        //根据前一天日期进行统计功能，分组操作
        //统计前一天的交易金额
        OrderStatistics orderStatistics = orderInfoMapper.selectOrderStatistics(yeseterday);
        //把统计后的数据，添加到统计结果表中
        if(orderStatistics != null) {
            orderStatisticsMapper.insert(orderStatistics) ;
        }

    }
}
