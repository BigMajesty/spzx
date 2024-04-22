package com.spzx.service.user.service.impl;

import com.spzx.service.user.service.SmsService;
import com.spzx.utils.HttpUtils;
import jakarta.annotation.Resource;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * ClassName: SmsServiceImpl
 * Package: com.spzx.service.user.service.impl
 * Description:
 * Author: zhj
 * Create: 2024/4/19 - 19:45
 * Version: v1.0
 */
@Service
public class SmsServiceImpl implements SmsService {

    @Autowired
    RedisTemplate<String,String> redisTemplate;

    /**
     * 发送验证码
     * @param phone
     */
    @Override
    public void sendValidateCode(String phone) {
        //验证码测试成功后就不再发送了，
        String  code = redisTemplate.opsForValue().get(phone);
        if(!StringUtils.isEmpty(code)){
            System.out.println(code);
            return;
        }

        //生成验证码
        code = RandomStringUtils.randomNumeric(4);      // 生成验证码

        //存储至redis，并设置过期时间
        redisTemplate.opsForValue().set(phone , code , 5 , TimeUnit.MINUTES);
        // 便于测试，不再真实发送验证码,直接查看redis数据库
        //向手机号发送验证码
        //sendMessage(phone,code);
    }

    /**
     * 发送短信至手机
     * @param phone
     * @param code
     */
    public void sendMessage(String phone,String code){
        String host = "https://dfsns.market.alicloudapi.com";
        String path = "/data/send_sms";
        String method = "POST";
        String appcode = "0d1546576a364530a2b42d0408d8aab1";
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        //根据API的要求，定义相对应的Content-Type
        headers.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        Map<String, String> querys = new HashMap<String, String>();
        Map<String, String> bodys = new HashMap<String, String>();
        bodys.put("content", "code:"+code);
        bodys.put("template_id", "CST_ptdie100");  //注意，CST_ptdie100该模板ID仅为调试使用，调试结果为"status": "OK" ，即表示接口调用成功，然后联系客服报备自己的专属签名模板ID，以保证短信稳定下发
        bodys.put("phone_number",phone);


        try {
            /**
             * 重要提示如下:
             * HttpUtils请从
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
             * 下载
             *
             * 相应的依赖请参照
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
             */
            HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
            System.out.println(response.toString());
            //获取response的body
            //System.out.println(EntityUtils.toString(response.getEntity()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
