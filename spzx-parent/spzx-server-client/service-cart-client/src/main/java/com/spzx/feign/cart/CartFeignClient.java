package com.spzx.feign.cart;

import com.spzx.model.entity.h5.CartInfo;
import com.spzx.model.vo.common.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * ClassName: CartFeignClient
 * Package: com.spzx.feign.cart
 * Description:
 * Author: zhj
 * Create: 2024/4/20 - 17:07
 * Version: v1.0
 */
@FeignClient(value = "service-cart")
public interface CartFeignClient {
    @GetMapping(value = "/api/order/cart/auth/getAllChecked")
    public abstract List<CartInfo> getAllChecked() ;

    @GetMapping(value = "/api/order/cart/auth/deleteChecked")
    public abstract Result deleteChecked();
}
