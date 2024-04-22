package com.spzx.manager.service;

import com.spzx.model.vo.system.ValidateCodeVo;

/**
 * ClassName: ValidateCodeService
 * Package: com.spzx.manager.service
 * Description:
 * Author: zhj
 * Create: 2024/4/12 - 11:36
 * Version: v1.0
 */
public interface ValidateCodeService {
    ValidateCodeVo generateValidateCode();
}
