package com.spzx.common.log.annotation;

import com.spzx.common.log.aspect.AspectLog;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ClassName: EnableLogAspect
 * Package: com.spzx.common.log.annotation
 * Description:
 * Author: zhj
 * Create: 2024/4/15 - 14:53
 * Version: v1.0
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import(value = AspectLog.class)            // 通过Import注解导入日志切面类到Spring容器中
public @interface EnableLogAspect {

}