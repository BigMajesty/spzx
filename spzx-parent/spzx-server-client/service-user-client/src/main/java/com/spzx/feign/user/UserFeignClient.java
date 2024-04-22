package com.spzx.feign.user;

import com.spzx.model.entity.user.UserAddress;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * ClassName: UserFeignClient
 * Package: com.spzx.feign.user
 * Description:
 * Author: zhj
 * Create: 2024/4/20 - 20:11
 * Version: v1.0
 */
@FeignClient(value = "service-user")
public interface UserFeignClient {
    @GetMapping("/api/user/userAddress/getUserAddress/{id}")
    public abstract UserAddress getUserAddress(@PathVariable Long id) ;
}
