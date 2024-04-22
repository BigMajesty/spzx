package com.spzx.service.user.service;

/**
 * ClassName: SmsService
 * Package: com.spzx.service.user.service
 * Description:
 * Author: zhj
 * Create: 2024/4/19 - 19:44
 * Version: v1.0
 */
public interface SmsService {
    void sendValidateCode(String phone);
}
