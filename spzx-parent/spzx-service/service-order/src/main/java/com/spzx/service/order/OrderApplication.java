package com.spzx.service.order;

import com.spzx.common.anno.EnableUserTokenFeignInterceptor;
import com.spzx.common.anno.EnableUserWebMvcConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * ClassName: OrderApplication
 * Package: com.spzx.service.order
 * Description:
 * Author: zhj
 * Create: 2024/4/20 - 21:21
 * Version: v1.0
 */
@SpringBootApplication
@EnableUserWebMvcConfiguration
@EnableFeignClients(basePackages = {"com.spzx.feign.cart","com.spzx.feign.user","com.spzx.feign.product"})
@EnableUserTokenFeignInterceptor//远程调用传递token拦截器，注解开启
public class OrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class , args) ;
    }
}
