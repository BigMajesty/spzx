package com.spzx.manager.service.impl;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.spzx.common.exception.GlobalException;
import com.spzx.manager.mapper.SysUserMapper;
import com.spzx.manager.mapper.SysUserRoleMapper;
import com.spzx.manager.service.SysUserService;
import com.spzx.model.dto.system.AssginRoleDto;
import com.spzx.model.dto.system.LoginDto;
import com.spzx.model.dto.system.SysUserDto;
import com.spzx.model.entity.system.SysUser;
import com.spzx.model.vo.common.ResultCodeEnum;
import com.spzx.model.vo.system.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * ClassName: SysUserServiceImpl
 * Package: com.spzx.manager.service.impl
 * Description:
 * Author: zhj
 * Create: 2024/4/11 - 19:21
 * Version: v1.0
 */
@Service
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    SysUserMapper sysUserMapper;
    @Autowired
    private RedisTemplate<String,String> redisTemplate;
    @Autowired
    SysUserRoleMapper sysUserRoleMapper;

    /**
     * 登录
     * @param loginDto
     * @return
     */
    @Override
    public LoginVo login(LoginDto loginDto) {
        //获取输入验证码和存储到redis中的key名称
        String captcha = loginDto.getCaptcha();
        String codeKey = loginDto.getCodeKey();
        //根据获取的redis的可以，查询验证码
        String redisCode = redisTemplate.opsForValue().get("user:validate" + codeKey);
        //比较输入的验证码和redis存储的验证码是否一致
        if(StrUtil.isEmpty(redisCode)||!StrUtil.endWithIgnoreCase(redisCode,captcha)){
            //如果不一致提示用户，校验失败
            throw new GlobalException(ResultCodeEnum.VALIDATECODE_ERROR);
        }
        //如果一致，删除redis里的验证码
        redisTemplate.delete("user:validate" + codeKey);


        //1.从loginDto获取提交用户名
        String userName = loginDto.getUserName();
        //2.根据用户名查询数据表sys_user表
        SysUser sysUser = sysUserMapper.selectUserInfoByUserName(userName);
        //3.如果根据用户名查询不到对应信息，用户不存在，返回错误信息
        if(sysUser==null){
            throw new GlobalException(ResultCodeEnum.LOGIN_ERROR);
        }
        //4.若查询到用户信息，则用户存在,获取输入密码，
        String database_password = sysUser.getPassword();
        String input_password = loginDto.getPassword();
        //5.比较输入的密码与数据的密码是否一致
            //把输入的密码进行加密，再进行数据库密码的比较，md5加密
        input_password = DigestUtils.md5DigestAsHex(input_password.getBytes());
        if(!input_password.equals(database_password)){
            throw new GlobalException(ResultCodeEnum.LOGIN_ERROR);
        }
        //6.如果密码一致，登录成功，否则，登录失败
        //7.登录成功，生成用户唯一标识token
        String token = UUID.randomUUID().toString().replaceAll("-","");
        //8.把登录成功用户信息放到redis里面
            //key:token   value:用户信息
        redisTemplate.opsForValue().set("user:login"+token,JSON.toJSONString(sysUser),7, TimeUnit.DAYS);
        //9.返回loginvo对象
        LoginVo loginVo=new LoginVo();
        loginVo.setToken(token);
        return loginVo;
    }

    /**
     * 获取用户登录信息
     * @param token
     * @return
     */
    @Override
    public SysUser getUserInfo(String token) {
        //根据token获取redis中存储的用户信息
        String userJson = redisTemplate.opsForValue().get("user:login" + token);
        //将Json字符串转为对象并返回
        SysUser sysUser=JSON.parseObject(userJson,SysUser.class);
        return sysUser;
    }

    /**
     * 用户退出
     * @param token
     */
    @Override
    public void logout(String token) {
        redisTemplate.delete("user:login"+token);
    }

    /**
     * 查询用户列表
     * @param pageNum
     * @param pageSize
     * @param sysUserDto
     * @return
     */
    @Override
    public PageInfo<SysUser> findByPage(Integer pageNum, Integer pageSize, SysUserDto sysUserDto) {
        PageHelper.startPage(pageNum,pageSize);
        List<SysUser> list = sysUserMapper.findByPage(sysUserDto);
        PageInfo<SysUser> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    /**
     * 增加用户
     * @param sysUser
     */
    @Override
    public void saveSysUser(SysUser sysUser) {
        //查询用户是否存在，若存在返回错误
        SysUser user = sysUserMapper.selectUserInfoByUserName(sysUser.getName());
        if(user!=null){
            throw new GlobalException(ResultCodeEnum.USER_NAME_IS_EXISTS);
        }
        //用户不存在，增加用户
            //加密密码
        String md5Password = DigestUtils.md5DigestAsHex(sysUser.getPassword().getBytes());
        sysUser.setPassword(md5Password);
        //status不能为空
        sysUser.setStatus(0);
        sysUserMapper.saveSysUser(sysUser);
    }

    /**
     * 修改用户信息
     * @param sysUser
     */
    @Override
    public void updateSysUser(SysUser sysUser) {
        sysUserMapper.updateSysUser(sysUser);
    }

    /**
     * 删除用户，逻辑删除
     * @param userId
     */
    @Override
    public void deleteById(Long userId) {
        sysUserMapper.deleteById(userId);
    }

    /**
     * 保存角色数据
     * @param assginRoleDto
     */
    @Override
    public void doAssign(AssginRoleDto assginRoleDto) {
        //删除之前的用户角色关系
        sysUserRoleMapper.delete(assginRoleDto.getUserId());
        //新增用户角色关系
        List<Long> roleIdList = assginRoleDto.getRoleIdList();
        for(Long roleId : roleIdList){
            sysUserRoleMapper.doAssign(roleId,assginRoleDto.getUserId() );
        }

    }


}
