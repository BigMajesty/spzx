package com.spzx.service.order.mapper;

import com.spzx.model.entity.order.OrderLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * ClassName: orderLogMapper
 * Package: com.spax.service.order.mapper
 * Description:
 * Author: zhj
 * Create: 2024/4/20 - 20:25
 * Version: v1.0
 */
@Mapper
public interface OrderLogMapper {
    //保存订单日志
    void save(OrderLog orderLog);
}
