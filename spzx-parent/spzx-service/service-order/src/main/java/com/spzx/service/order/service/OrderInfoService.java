package com.spzx.service.order.service;

import com.github.pagehelper.PageInfo;
import com.spzx.model.dto.h5.OrderInfoDto;
import com.spzx.model.entity.order.OrderInfo;
import com.spzx.model.vo.h5.TradeVo;

/**
 * ClassName: OrderInfo
 * Package: com.spax.service.order.service
 * Description:
 * Author: zhj
 * Create: 2024/4/20 - 17:15
 * Version: v1.0
 */
public interface OrderInfoService {
    TradeVo trade();

    Long submitOrder(OrderInfoDto orderInfoDto);

    OrderInfo getOrderInfo(Long orderId);

    TradeVo buy(Long skuId);

    PageInfo<OrderInfo> list(Integer page, Integer limit, Integer orderStatus);

    OrderInfo getOrderInfoByOrderNo(String orderNo);
}
