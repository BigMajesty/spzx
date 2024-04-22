package com.spzx.common.config;

import com.spzx.common.interceptors.UserLoginAuthInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * ClassName: UserWebMvcConfiguration
 * Package: com.spzx.service.user.config
 * Description:
 * Author: zhj
 * Create: 2024/4/20 - 9:47
 * Version: v1.0
 */
@Configuration
public class UserWebMvcConfiguration implements WebMvcConfigurer {
    @Autowired
    private UserLoginAuthInterceptor userLoginAuthInterceptor ;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(userLoginAuthInterceptor)
                .addPathPatterns("/api/**");
    }
}
