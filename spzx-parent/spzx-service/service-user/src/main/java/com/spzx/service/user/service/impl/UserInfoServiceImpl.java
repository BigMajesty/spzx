package com.spzx.service.user.service.impl;

import com.alibaba.fastjson.JSON;
import com.spzx.common.exception.GlobalException;
import com.spzx.model.dto.h5.UserLoginDto;
import com.spzx.model.dto.h5.UserRegisterDto;
import com.spzx.model.entity.user.UserInfo;
import com.spzx.model.vo.common.ResultCodeEnum;
import com.spzx.model.vo.h5.UserInfoVo;
import com.spzx.service.user.mapper.UserInfoMapper;
import com.spzx.service.user.service.UserInfoService;
import com.spzx.utils.AuthContextUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * ClassName: UserInfoServiceImpl
 * Package: com.spzx.service.user.service.impl
 * Description:
 * Author: zhj
 * Create: 2024/4/19 - 21:26
 * Version: v1.0
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    UserInfoMapper userInfoMapper;
    @Autowired
    RedisTemplate<String,String> redisTemplate;
    /**
     * 会员注册
     * @param userRegisterDto
     */
    @Override
    public void register(UserRegisterDto userRegisterDto) {
        //获取数据，并检验数据是否合法
        String username = userRegisterDto.getUsername();
        String password = userRegisterDto.getPassword();
        String nickName = userRegisterDto.getNickName();
        String code = userRegisterDto.getCode();
        if(StringUtils.isEmpty(username)||StringUtils.isEmpty(password)||
        StringUtils.isEmpty(nickName)||StringUtils.isEmpty(code)){
            //存在数据为空，抛出异常
            throw new GlobalException(ResultCodeEnum.DATA_ERROR);
        }
        //验证码校验
            //从redis中获取发送的验证码
        String redisCode = redisTemplate.opsForValue().get(username);//用户名就是手机号
        if(!redisCode.equals(code)){
            throw new GlobalException(ResultCodeEnum.VALIDATECODE_ERROR);
        }
        //校验用户名，不可重复
        UserInfo userInfo = userInfoMapper.selectUserInfoByUsername(username);
        if(userInfo!=null){
            throw new GlobalException(ResultCodeEnum.USER_NAME_IS_EXISTS);
        }
        //封装数据并向向数据库添加
        userInfo = new UserInfo();
        userInfo.setUsername(username);
        userInfo.setNickName(nickName);
        userInfo.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
        userInfo.setPhone(username);
        userInfo.setStatus(1);
        userInfo.setSex(0);
        userInfo.setAvatar("http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eoj0hHXhgJNOTSOFsS4uZs8x1ConecaVOB8eIl115xmJZcT4oCicvia7wMEufibKtTLqiaJeanU2Lpg3w/132");
        userInfoMapper.register(userInfo);

        //已经注册，将验证码从redis中删除
        redisTemplate.delete(username);
    }

    /**
     * 用户登录
     * @param userLoginDto
     * @return
     */
    @Override
    public String login(UserLoginDto userLoginDto) {
        //获取用户登录数据
        String username = userLoginDto.getUsername();
        String password = userLoginDto.getPassword();
        //校验用户名和密码
        if(StringUtils.isEmpty(username)||StringUtils.isEmpty(password)){
            //为空
            throw new GlobalException(ResultCodeEnum.DATA_ERROR);
        }
        UserInfo userInfo = userInfoMapper.selectUserInfoByUsername(username);
        if(userInfo==null){
            //用户不存在
            throw  new GlobalException(ResultCodeEnum.LOGIN_ERROR);
        }
        String md5Password =DigestUtils.md5DigestAsHex(password.getBytes());
        if(!md5Password.equals(userInfo.getPassword())){
            //密码错误
            throw new GlobalException(ResultCodeEnum.LOGIN_ERROR);
        }
        //检验是否被禁用
        if(userInfo.getStatus()==0){
            throw new GlobalException(ResultCodeEnum.ACCOUNT_STOP);
        }

        //生成token,存储至redis并返回
        String token = UUID.randomUUID().toString().replaceAll("-","");
        redisTemplate.opsForValue().set("H5:user:"+token, JSON.toJSONString(userInfo),30, TimeUnit.DAYS);
        return token;
    }

    /**
     * 登录后获取用户信息
     * @param token
     * @return
     */
    @Override
    public UserInfoVo getCurrentUserInfo(String token) {
//        //根据token查询redis中的用户信息
//        String userInfoStr = redisTemplate.opsForValue().get("H5:user:" + token);
//        if(StringUtils.isEmpty(userInfoStr)){
//            //用户信息不存在，登录失效
//            throw new GlobalException(ResultCodeEnum.LOGIN_AUTH);
//        }
//        //封装用户信息并返回
//        UserInfo userInfo = JSON.parseObject(userInfoStr,UserInfo.class) ;
//        UserInfoVo userInfoVo = new UserInfoVo();
//        if (userInfo != null) {
//            userInfoVo.setNickName(userInfo.getNickName());
//        }
//        if (userInfo != null) {
//            userInfoVo.setAvatar(userInfo.getAvatar());
//        }
        //通过ThreadLocal获取
        UserInfo userInfo = AuthContextUtil.getUserInfo();
        UserInfoVo userInfoVo = new UserInfoVo();
        BeanUtils.copyProperties(userInfo, userInfoVo);
        return userInfoVo;
    }
}
