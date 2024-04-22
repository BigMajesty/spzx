package com.spzx.service.cart.service.impl;

import com.alibaba.fastjson2.JSON;
import com.spzx.feign.product.ProductFeignClient;
import com.spzx.model.entity.h5.CartInfo;
import com.spzx.model.entity.product.ProductSku;
import com.spzx.model.entity.user.UserInfo;
import com.spzx.service.cart.service.CartService;
import com.spzx.utils.AuthContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * ClassName: CardServiceImpl
 * Package: com.spzx.service.cart.service.impl
 * Description:
 * Author: zhj
 * Create: 2024/4/20 - 11:22
 * Version: v1.0
 */
@Service
public class CartServiceImpl implements CartService {

    @Autowired
    ProductFeignClient productFeignClient;

    @Autowired
    RedisTemplate<String,String> redisTemplate;
    /**
     * 添加商品至购物车
     * @param skuId
     * @param skuNum
     */
    @Override
    public void addToCart(Long skuId, Integer skuNum) {
        //1.必须为登录状态，获取当前用户的id
        //从ThreadLocal获取用户信息
        UserInfo userInfo = AuthContextUtil.getUserInfo();
        Long userId = AuthContextUtil.getUserInfo().getId();
        //购物车存储类型为hash，键的格式如下：user:cart:+userId
        String cartKey = "user:cart:"+userId;
        //2.从redis里获取购物车数据，用户的购物车是存储再redis里的，
        Object cartInfoObj = redisTemplate.opsForHash().get(cartKey, skuId.toString());
        //3.判断购物车中是否已经存在商品sku
        CartInfo cartInfo = null;
        if(cartInfoObj!=null){
            cartInfo = JSON.parseObject(cartInfoObj.toString(), CartInfo.class);
            cartInfo.setSkuNum((cartInfo.getSkuNum())+skuNum);//商品数量相加
            //设置购物车中的商品为被选中的状态
            cartInfo.setIsChecked(1);
            cartInfo.setUpdateTime(new Date());

        }else{
            //4.若不存在，将商品添加至购物车（redis）,远程调用openfeign，获取商品的sku
            //封装数据
            cartInfo=new CartInfo();
            ProductSku productSku = productFeignClient.getBySkuId(skuId);
            cartInfo.setUserId(userId);
            cartInfo.setSkuId(skuId);
            cartInfo.setCartPrice(productSku.getSalePrice());
            cartInfo.setSkuNum(skuNum);
            cartInfo.setImgUrl(productSku.getThumbImg());
            cartInfo.setSkuName(productSku.getSkuName());
            cartInfo.setIsChecked(1);
            cartInfo.setCreateTime(new Date());
            cartInfo.setUpdateTime(new Date());
        }


        //将商品数据存储到购物车
        redisTemplate.opsForHash().put(cartKey,skuId.toString(),JSON.toJSONString(cartInfo));

    }

    /**
     * 查询购物车列表
     * @return
     */
    @Override
    public List<CartInfo> getCartList() {
        //获取用户id
        Long userId = AuthContextUtil.getUserInfo().getId();
        //根据用户id获取购物车键
        String cartKey = "user:cart:"+userId;
        //查询购物车数据
        List<Object> cartInfoList = redisTemplate.opsForHash().values(cartKey);
        //将cartInfoList转为List<CartInfo>
        if(!CollectionUtils.isEmpty(cartInfoList)){
            List<CartInfo> list = cartInfoList.stream()
                    .map(cartInfoJson -> JSON.parseObject(cartInfoJson.toString(),CartInfo.class))
                    .sorted((o1,o2)->o2.getCreateTime().compareTo(o1.getCreateTime()))
                    .toList();
            return list;
        }

        return new ArrayList<>();
    }

    /**
     * 删除购物车商品
     * @param skuId
     */
    @Override
    public void deleteCart(Long skuId) {
        Long userId = AuthContextUtil.getUserInfo().getId();
        String cartKey = "user:cart:"+userId;
        //删除购物车指定商品的数据
        redisTemplate.opsForHash().delete(cartKey,skuId.toString());
    }

    /**
     * 更新购物车商品选中状态
     * @param skuId
     * @param isChecked
     */
    @Override
    public void checkCart(Long skuId, Integer isChecked) {
        Long userId = AuthContextUtil.getUserInfo().getId();
        String cartKey = "user:cart:"+userId;
        //获取原商品数据
        Boolean hasKey = redisTemplate.opsForHash().hasKey(cartKey, skuId.toString());
        //是否存在，按理说不用判断，如果能操作选中状态那说明可定存在啊？
        if(hasKey){
            Object cartInfoObj = redisTemplate.opsForHash().get(cartKey, skuId.toString());
            CartInfo cartInfo = JSON.parseObject(cartInfoObj.toString(), CartInfo.class);
            //更新选中状态值
            cartInfo.setIsChecked(isChecked);
            redisTemplate.opsForHash().put(cartKey,skuId.toString(),JSON.toJSONString(cartInfo));
        }
    }

    /**
     * 更新购物车状态，全选全不选
     * @param isChecked
     */
    @Override
    public void allCheckCart(Integer isChecked) {
        //获取用户id
        Long userId = AuthContextUtil.getUserInfo().getId();
        //根据用户id获取购物车键
        String cartKey = "user:cart:"+userId;
        //查询购物车数据
        List<Object> cartInfoList = redisTemplate.opsForHash().values(cartKey);
        //将cartInfoList转为List<CartInfo>
        if(!CollectionUtils.isEmpty(cartInfoList)){
           for(Object o : cartInfoList){
               CartInfo cartInfo = JSON.parseObject(o.toString(), CartInfo.class);
               cartInfo.setIsChecked(isChecked);
               redisTemplate.opsForHash().put(cartKey,cartInfo.getSkuId().toString(),JSON.toJSONString(cartInfo));
           }
        }
    }

    /**
     * 清空购物车
     */
    @Override
    public void clearCart() {
        //获取用户id
        Long userId = AuthContextUtil.getUserInfo().getId();
        //根据用户id获取购物车键
        String cartKey = "user:cart:"+userId;
        redisTemplate.delete(cartKey);
    }

    /**
     * 获取选中的购物车列表
     * @return
     */
    @Override
    public List<CartInfo> getAllChecked() {
        //获取用户id
        Long userId = AuthContextUtil.getUserInfo().getId();
        //根据用户id获取购物车键
        String cartKey = "user:cart:"+userId;
        //查询购物车数据
        List<Object> cartInfoList = redisTemplate.opsForHash().values(cartKey);
        //将cartInfoList转为List<CartInfo>
        if(!CollectionUtils.isEmpty(cartInfoList)){
            List<CartInfo> list = cartInfoList.stream()
                    .map(cartInfoJson -> JSON.parseObject(cartInfoJson.toString(),CartInfo.class))
                    .sorted((o1,o2)->o2.getCreateTime().compareTo(o1.getCreateTime()))
                    .filter(cartInfo -> cartInfo.getIsChecked()==1)//过滤掉未被选中的商品
                    .toList();
            return list;
        }
        return new ArrayList<>();
    }
    /**
     * 订单提交后，清除已经选中的商品
     */
    @Override
    public void deleteChecked() {
        //获取用户id
        Long userId = AuthContextUtil.getUserInfo().getId();
        //根据用户id获取购物车键
        String cartKey = "user:cart:"+userId;
        //查询购物车数据
        List<Object> cartInfoList = redisTemplate.opsForHash().values(cartKey);
        //将cartInfoList转为List<CartInfo>
        if(!CollectionUtils.isEmpty(cartInfoList)){
            cartInfoList.stream()
                .map(cartInfoJson -> JSON.parseObject(cartInfoJson.toString(),CartInfo.class))
                .filter(cartInfo -> cartInfo.getIsChecked()==1)//过滤掉未被选中的商品
                .forEach(cartInfo -> redisTemplate.opsForHash().delete(cartKey,cartInfo.getSkuId().toString()));
        }
    }


}
