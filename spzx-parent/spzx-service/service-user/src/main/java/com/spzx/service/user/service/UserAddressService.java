package com.spzx.service.user.service;

import com.spzx.model.entity.user.UserAddress;

import java.util.List;

/**
 * ClassName: UserAddressService
 * Package: com.spzx.service.user.service
 * Description:
 * Author: zhj
 * Create: 2024/4/20 - 16:51
 * Version: v1.0
 */
public interface UserAddressService {
    List<UserAddress> findUserAddressList();

    UserAddress getUserAddress(Long id);
}
