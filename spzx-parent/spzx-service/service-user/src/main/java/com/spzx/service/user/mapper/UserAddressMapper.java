package com.spzx.service.user.mapper;

import com.spzx.model.entity.user.UserAddress;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * ClassName: UserAddressMapper
 * Package: com.spzx.service.user.mapper
 * Description:
 * Author: zhj
 * Create: 2024/4/20 - 16:51
 * Version: v1.0
 */
@Mapper
public interface UserAddressMapper {
    //获取用户地址列表
    List<UserAddress> findUserAddressList(Long userId);

    //获取生成订单中的地址
    UserAddress getUserAddress(Long id);
}
