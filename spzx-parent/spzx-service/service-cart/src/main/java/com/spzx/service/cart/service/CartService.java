package com.spzx.service.cart.service;

import com.spzx.model.entity.h5.CartInfo;

import java.util.List;

/**
 * ClassName: CardService
 * Package: com.spzx.service.cart.service
 * Description:
 * Author: zhj
 * Create: 2024/4/20 - 11:22
 * Version: v1.0
 */
public interface CartService {
    void addToCart(Long skuId, Integer skuNum);

    List<CartInfo> getCartList();

    void deleteCart(Long skuId);

    void checkCart(Long skuId, Integer isChecked);

    void allCheckCart(Integer isChecked);

    void clearCart();

    List<CartInfo> getAllChecked();

    void deleteChecked();
}
