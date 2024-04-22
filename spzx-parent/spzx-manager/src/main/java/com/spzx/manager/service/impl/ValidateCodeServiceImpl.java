package com.spzx.manager.service.impl;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import com.alibaba.fastjson2.util.UUIDUtils;
import com.spzx.manager.service.ValidateCodeService;
import com.spzx.model.vo.system.ValidateCodeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * ClassName: ValidateCodeServiceImpl
 * Package: com.spzx.manager.service.impl
 * Description:
 * Author: zhj
 * Create: 2024/4/12 - 11:36
 * Version: v1.0
 */
@Service
public class ValidateCodeServiceImpl implements ValidateCodeService {
    //生成图片验证码
    @Autowired
    private RedisTemplate<String,String> redisTemplate;
    @Override
    public ValidateCodeVo generateValidateCode() {
        //1.通过hutool生成验证码
        CircleCaptcha circleCaptcha = CaptchaUtil.createCircleCaptcha(150, 48, 4, 2);
        String code = circleCaptcha.getCode();//生成的4位验证码
        String imageBase64 = circleCaptcha.getImageBase64();//验证码图片信息，base64位编码
        //2.把验证码存储到redis里，key：uuid，  value：验证码值
            //设置过期时间
        String key = UUID.randomUUID().toString().replaceAll("-", "");
        redisTemplate.opsForValue().set("user:validate"+key,code,5, TimeUnit.MINUTES);
        //3.返回ValidateCodeVo对象
        ValidateCodeVo validateCodeVo=new ValidateCodeVo();
        validateCodeVo.setCodeKey(key);
        validateCodeVo.setCodeValue("data:image/png;base64," + imageBase64);
        return validateCodeVo;
    }
}
