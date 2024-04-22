package com.spzx.feign.order;

import com.spzx.model.entity.order.OrderInfo;
import com.spzx.model.vo.common.Result;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * ClassName: OrderFeignClient
 * Package: com.spzx.feign.order
 * Description:
 * Author: zhj
 * Create: 2024/4/21 - 8:53
 * Version: v1.0
 */
//远程调用order
@FeignClient(value = "service-order")
public interface OrderFeignClient {
    @GetMapping("/api/order/orderInfo/auth/getOrderInfoByOrderNo/{orderNo}")
    public Result<OrderInfo> getOrderInfoByOrderNo(
            @Parameter(name = "orderId", description = "订单id", required = true)
                                                   @PathVariable String orderNo);
}
