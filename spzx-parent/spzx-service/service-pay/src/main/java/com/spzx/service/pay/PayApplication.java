package com.spzx.service.pay;

import com.spzx.common.anno.EnableUserWebMvcConfiguration;
import com.spzx.service.pay.utils.AlipayProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * ClassName: PayApplication
 * Package: com.spzx.service.pay
 * Description:
 * Author: zhj
 * Create: 2024/4/21 - 8:42
 * Version: v1.0
 */
@SpringBootApplication
@EnableUserWebMvcConfiguration
@EnableConfigurationProperties(value = {AlipayProperties.class})
@EnableFeignClients(basePackages = {
        "com.spzx.feign.order"
})
public class PayApplication {

    public static void main(String[] args) {
        SpringApplication.run(PayApplication.class , args) ;
    }

}