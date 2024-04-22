package com.spzx.service.order.mapper;

import com.spzx.model.entity.order.OrderInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * ClassName: OrderInfoMapper
 * Package: com.spax.service.order.mapper
 * Description:
 * Author: zhj
 * Create: 2024/4/20 - 17:16
 * Version: v1.0
 */
@Mapper
public interface OrderInfoMapper {
    //保存订单信息
    void save(OrderInfo orderInfo);

    //根据订单id获取订单信息
    OrderInfo getById(Long orderId);

    //根据用户id和订单状态查询订单信息列表
    List<OrderInfo> findUserPage(Long userId, Integer orderStatus);
    //根据订单id获取订单信息
    OrderInfo getOrderInfoByOrderNo(String orderNo);
}
