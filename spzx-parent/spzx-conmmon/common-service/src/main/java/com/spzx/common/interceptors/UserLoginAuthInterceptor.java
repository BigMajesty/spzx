package com.spzx.common.interceptors;

import com.alibaba.fastjson2.JSON;
import com.spzx.model.entity.user.UserInfo;
import com.spzx.utils.AuthContextUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * ClassName: LoginAuthIntercepter
 * Package: com.spzx.service.user.intercepters
 * Description:
 * Author: zhj
 * Create: 2024/4/20 - 9:22
 * Version: v1.0
 */
@Component
public class UserLoginAuthInterceptor implements HandlerInterceptor {

    @Autowired
    RedisTemplate<String,String> redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        UserInfo userInfo = JSON.parseObject(redisTemplate.opsForValue().get("H5:user:" + token), UserInfo.class);
        AuthContextUtil.setUserInfo(userInfo);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
