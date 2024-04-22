package com.spzx.common.log.service;

import com.spzx.model.entity.system.SysOperLog;

/**
 * ClassName: AsyncOperLogService
 * Package: com.spzx.common.log.service
 * Description:
 * Author: zhj
 * Create: 2024/4/15 - 15:12
 * Version: v1.0
 */
public interface AsyncOperLogService {			// 保存日志数据
    public abstract void saveSysOperLog(SysOperLog sysOperLog) ;
}