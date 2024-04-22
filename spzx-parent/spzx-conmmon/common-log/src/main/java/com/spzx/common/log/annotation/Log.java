package com.spzx.common.log.annotation;

import com.spzx.common.log.enums.OperatorType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ClassName: log
 * Package: com.spzx.common.log.annotation
 * Description:
 * Author: zhj
 * Create: 2024/4/15 - 14:36
 * Version: v1.0
 */

@Target({ElementType.METHOD})//表示可以用在什么地方：方法
@Retention(RetentionPolicy.RUNTIME)//用在什么时期
public @interface Log {     //自定义操作日志注解

    public String title() ;								// 模块名称
    public OperatorType operatorType() default OperatorType.MANAGE;	// 操作人类别
    public int businessType() ;     // 业务类型（0其它 1新增 2修改 3删除）
    public boolean isSaveRequestData() default true;   // 是否保存请求的参数
    public boolean isSaveResponseData() default true;  // 是否保存响应的参数
}
