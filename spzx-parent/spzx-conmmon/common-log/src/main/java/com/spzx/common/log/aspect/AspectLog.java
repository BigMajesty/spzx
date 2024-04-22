package com.spzx.common.log.aspect;

import com.spzx.common.log.annotation.Log;
import com.spzx.common.log.service.AsyncOperLogService;
import com.spzx.common.log.utils.LogUtil;
import com.spzx.model.entity.system.SysOperLog;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * ClassName: AspectLog
 * Package: com.spzx.common.log.aspect
 * Description:
 * Author: zhj
 * Create: 2024/4/15 - 14:44
 * Version: v1.0
 */
@Aspect
@Component
public class AspectLog {

    @Autowired
    AsyncOperLogService asyncOperLogService;

    //环绕通知
    @Around(value = "@annotation(sysLog)")
    public Object doAroundAdvice(ProceedingJoinPoint joinPoint, Log sysLog){
        //业务方法前执行
        SysOperLog sysOperLog = new SysOperLog();
        LogUtil.beforeHandleLog(sysLog,joinPoint,sysOperLog);

        //业务方法

        Object proceed = null;
        try {
            proceed = joinPoint.proceed();
            //业务方法之后执行,这里是正常记录
            LogUtil.afterHandlLog(sysLog,joinPoint,sysOperLog,0,null);
        } catch (Throwable e) {
            //业务方法未正常执行，
            LogUtil.afterHandlLog(sysLog,joinPoint,sysOperLog,1,e.getMessage());
            throw new RuntimeException(e);
        }

        //调用service方法把日志信息添加到数据库中
        asyncOperLogService.saveSysOperLog(sysOperLog);

        return proceed;
    }
}
