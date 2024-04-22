package com.spzx.common.anno;

import com.spzx.common.interceptors.UserTokenFeignInterceptor;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ClassName: EnableUserTokenFeignInterceptor
 * Package: com.spzx.common.anno
 * Description:
 * Author: zhj
 * Create: 2024/4/20 - 19:42
 * Version: v1.0
 */
//定义使用拦截器的注解
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import(value = UserTokenFeignInterceptor.class)
public @interface EnableUserTokenFeignInterceptor {
}
