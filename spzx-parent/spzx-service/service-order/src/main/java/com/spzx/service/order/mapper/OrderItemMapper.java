package com.spzx.service.order.mapper;

import com.spzx.model.entity.order.OrderItem;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * ClassName: orderItemMapper
 * Package: com.spax.service.order.mapper
 * Description:
 * Author: zhj
 * Create: 2024/4/20 - 20:24
 * Version: v1.0
 */
@Mapper
public interface OrderItemMapper {
    //保存订单明细
    void save(OrderItem orderItem);

    //根据订单id查询订单列表项
    List<OrderItem> findByOrderId(Long id);
}
