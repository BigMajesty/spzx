package com.spzx.manager.mapper;

import com.spzx.model.entity.system.SysOperLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * ClassName: SysOperLogMapper
 * Package: com.spzx.manager.mapper
 * Description:
 * Author: zhj
 * Create: 2024/4/15 - 15:14
 * Version: v1.0
 */
@Mapper
public interface SysOperLogMapper {
    //保存日志信息
    void insert(SysOperLog sysOperLog);
}
