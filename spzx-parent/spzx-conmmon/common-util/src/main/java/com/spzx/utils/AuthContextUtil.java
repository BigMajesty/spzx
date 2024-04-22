package com.spzx.utils;

import com.spzx.model.entity.system.SysUser;
import com.spzx.model.entity.user.UserInfo;

/**
 * ClassName: AuthContextUtil
 * Package: com.spzx.utils
 * Description:
 * Author: zhj
 * Create: 2024/4/12 - 16:19
 * Version: v1.0
 */
public class AuthContextUtil {
    //创建ThreadLocal对象
    private static final ThreadLocal<SysUser> THREAD_LOCAL = new ThreadLocal<>();
    private static final ThreadLocal<UserInfo> userInfoThreadLocal = new ThreadLocal<>() ;
    //添加数据
    public static void set(SysUser sysUser){
        THREAD_LOCAL.set(sysUser);
    }
    //获取数据
    public static SysUser get(){
        return THREAD_LOCAL.get();
    }
    //删除数据
    public static void remove(){
        THREAD_LOCAL.remove();
    }

    // 定义存储数据的静态方法
    public static void setUserInfo(UserInfo userInfo) {
        userInfoThreadLocal.set(userInfo);
    }

    // 定义获取数据的方法
    public static UserInfo getUserInfo() {
        return userInfoThreadLocal.get() ;
    }

    // 删除数据的方法
    public static void removeUserInfo() {
        userInfoThreadLocal.remove();
    }
}
