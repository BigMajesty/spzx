package com.spzx.service.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * ClassName: ProductApplication
 * Package: com.spzx.product
 * Description:
 * Author: zhj
 * Create: 2024/4/19 - 8:58
 * Version: v1.0
 */
@SpringBootApplication
@EnableCaching//注解开发数据存储redis
public class ProductApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProductApplication.class, args);
    }

}
