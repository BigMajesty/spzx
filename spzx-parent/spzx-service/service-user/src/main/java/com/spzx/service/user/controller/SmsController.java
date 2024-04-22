package com.spzx.service.user.controller;

import com.spzx.model.vo.common.Result;
import com.spzx.model.vo.common.ResultCodeEnum;
import com.spzx.service.user.service.SmsService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: SmsController
 * Package: com.spzx.service.user.controller
 * Description:
 * Author: zhj
 * Create: 2024/4/19 - 19:44
 * Version: v1.0
 */
@RestController
@RequestMapping("api/user/sms")
public class SmsController {
    @Resource
    SmsService smsService;

    /**
     * 短信发送
     * @param phone
     * @return
     */
    @GetMapping(value = "/sendCode/{phone}")
    public Result sendValidateCode(@PathVariable String phone) {
        smsService.sendValidateCode(phone);
        return Result.build(null , ResultCodeEnum.SUCCESS) ;
    }
}
