package com.spzx.manager.service.impl;

import com.spzx.common.log.service.AsyncOperLogService;
import com.spzx.manager.mapper.SysOperLogMapper;
import com.spzx.model.entity.system.SysOperLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ClassName: AsyncOperLogServiceImpl
 * Package: com.spzx.manager.service.impl
 * Description:
 * Author: zhj
 * Create: 2024/4/15 - 15:12
 * Version: v1.0
 */
@Service
public class AsyncOperLogServiceImpl implements AsyncOperLogService {

    @Autowired
    SysOperLogMapper sysOperLogMapper;

    //保存日志文件
    @Override
    public void saveSysOperLog(SysOperLog sysOperLog) {
        sysOperLogMapper.insert(sysOperLog);
    }
}
