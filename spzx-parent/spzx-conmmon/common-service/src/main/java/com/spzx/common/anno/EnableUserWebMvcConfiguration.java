package com.spzx.common.anno;

import com.spzx.common.config.UserWebMvcConfiguration;
import com.spzx.common.interceptors.UserLoginAuthInterceptor;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ClassName: EnableUserWebMvcConfiguration
 * Package: com.spzx.common.anno
 * Description:
 * Author: zhj
 * Create: 2024/4/20 - 15:41
 * Version: v1.0
 */
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = ElementType.TYPE)
@Import(value = { UserLoginAuthInterceptor.class , UserWebMvcConfiguration.class})
public @interface EnableUserWebMvcConfiguration {

}
