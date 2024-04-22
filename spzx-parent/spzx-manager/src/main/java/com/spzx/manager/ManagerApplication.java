package com.spzx.manager;

import com.spzx.common.log.annotation.EnableLogAspect;
import com.spzx.manager.properties.MinioProperties;
import com.spzx.manager.properties.UserProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * ClassName: ManagerApplication
 * Package: com.spzx.manager
 * Description:
 * Author: zhj
 * Create: 2024/4/11 - 19:16
 * Version: v1.0
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.spzx"})
@EnableConfigurationProperties(value = {UserProperties.class, MinioProperties.class})
@EnableScheduling//启动定时任务
@EnableLogAspect
public class ManagerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ManagerApplication.class,args);
    }
}
