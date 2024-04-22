package com.spzx.service.user.service.impl;

import com.spzx.model.entity.user.UserAddress;
import com.spzx.service.user.mapper.UserAddressMapper;
import com.spzx.service.user.service.UserAddressService;
import com.spzx.utils.AuthContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName: UserAddressServiceImpl
 * Package: com.spzx.service.user.service.impl
 * Description:
 * Author: zhj
 * Create: 2024/4/20 - 16:51
 * Version: v1.0
 */
@Service
public class UserAddressServiceImpl implements UserAddressService {
    @Autowired
    UserAddressMapper userAddressMapper;

    /**
     * 获取用户地址
     * @return
     */
    @Override
    public List<UserAddress> findUserAddressList() {
        //获取用户id
        Long userId = AuthContextUtil.getUserInfo().getId();
        //根据id获取用户地址
        return userAddressMapper.findUserAddressList(userId);
    }

    /**
     * 获取生成订单中的用户地址
     * @param id
     * @return
     */
    @Override
    public UserAddress getUserAddress(Long id) {
        return userAddressMapper.getUserAddress(id);
    }
}
