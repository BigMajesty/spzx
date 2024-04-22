package com.spzx.service.user;

import com.spzx.common.anno.EnableUserWebMvcConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * ClassName: UserApplication
 * Package: com.spzx.service.user
 * Description:
 * Author: zhj
 * Create: 2024/4/19 - 19:41
 * Version: v1.0
 */
@SpringBootApplication
@EnableUserWebMvcConfiguration
public class UserApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }

}
