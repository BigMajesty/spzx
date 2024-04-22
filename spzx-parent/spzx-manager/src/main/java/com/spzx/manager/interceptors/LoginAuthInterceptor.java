package com.spzx.manager.interceptors;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.spzx.model.entity.system.SysUser;
import com.spzx.model.vo.common.Result;
import com.spzx.model.vo.common.ResultCodeEnum;
import com.spzx.utils.AuthContextUtil;
import io.swagger.v3.core.util.Json;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

/**
 * ClassName: LoginAuthInterceptor
 * Package: com.spzx.manager.interceptors
 * Description:
 * Author: zhj
 * Create: 2024/4/12 - 16:23
 * Version: v1.0
 */
@Component
public class LoginAuthInterceptor implements HandlerInterceptor {

    @Autowired
    RedisTemplate<String,String> redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取请求方式
            //如果请求方式位options：预检请求，直接放行
        String method = request.getMethod();
        if(method.equals("OPTIONS")){
            return true;
        }
        //如果不是预检，从请求头中获取token
        String token = request.getHeader("token");
        //如果token为空，返回错误提示
        if(StrUtil.isEmpty(token)){
            responseNoLoginInfo(response);
            return false;
        }
        //如果token不为空，通过token查询redis，
            //查询不到返回错误信息
        String userInfo = redisTemplate.opsForValue().get("user:login" + token);
        if(StrUtil.isEmpty(userInfo)){
            responseNoLoginInfo(response);
            return false;
        }
        //如果查询到，将用信息存入ThreadLocal中
        SysUser sysUser = JSON.parseObject(userInfo, SysUser.class);
        AuthContextUtil.set(sysUser);
        //把redis用户信息数据过期时间延长
        redisTemplate.expire("user:login"+token,30, TimeUnit.MINUTES);
        //放行
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
         //删除ThreadLocal存放的信息
    }

    //响应208状态码给前端
    private void responseNoLoginInfo(HttpServletResponse response) {
        Result<Object> result = Result.build(null, ResultCodeEnum.LOGIN_AUTH);
        PrintWriter writer = null;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        try {
            writer = response.getWriter();
            writer.print(JSON.toJSONString(result));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) writer.close();
        }
    }
}
