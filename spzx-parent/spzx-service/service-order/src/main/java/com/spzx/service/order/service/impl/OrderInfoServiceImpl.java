package com.spzx.service.order.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.spzx.common.exception.GlobalException;
import com.spzx.model.entity.order.OrderInfo;
import com.spzx.model.entity.order.OrderLog;
import com.spzx.model.entity.product.ProductSku;
import com.spzx.model.entity.user.UserAddress;
import com.spzx.model.entity.user.UserInfo;
import com.spzx.model.vo.common.ResultCodeEnum;
import com.spzx.model.vo.h5.UserInfoVo;
import com.spzx.service.order.mapper.OrderInfoMapper;
import com.spzx.service.order.mapper.OrderItemMapper;
import com.spzx.service.order.mapper.OrderLogMapper;
import com.spzx.service.order.service.OrderInfoService;
import com.spzx.feign.cart.CartFeignClient;
import com.spzx.feign.product.ProductFeignClient;
import com.spzx.feign.user.UserFeignClient;
import com.spzx.model.dto.h5.OrderInfoDto;
import com.spzx.model.entity.h5.CartInfo;
import com.spzx.model.entity.order.OrderItem;
import com.spzx.model.vo.h5.TradeVo;
import com.spzx.utils.AuthContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: OrderInfoServiceImpl
 * Package: com.spax.service.order.service.impl
 * Description:
 * Author: zhj
 * Create: 2024/4/20 - 17:16
 * Version: v1.0
 */
@Service
public class OrderInfoServiceImpl implements OrderInfoService {
    @Autowired
    CartFeignClient cartFeignClient;
    @Autowired
    ProductFeignClient productFeignClient;
    @Autowired
    UserFeignClient userFeignClient;

    @Autowired
    OrderInfoMapper orderInfoMapper;
    @Autowired
    OrderItemMapper orderItemMapper;
    @Autowired
    OrderLogMapper orderLogMapper;

    /**
     * 生成订单数据并返回
     * @return
     */
    @Override
    public TradeVo trade() {
        //获取用户id和购物车键名
        Long userId = AuthContextUtil.getUserInfo().getId();
        String cartKey = "user:cart:"+userId;

        //根据键查询购物车选中状态的列表,远程调用微服务
        List<CartInfo> cartInfoList = cartFeignClient.getAllChecked();
        //将CartInfo数据转为订单明细数据
        List<OrderItem> orderItemList = new ArrayList<>();
        for(CartInfo cartInfo : cartInfoList){
            OrderItem orderItem = new OrderItem();
            orderItem.setSkuId(cartInfo.getSkuId());
            orderItem.setSkuName(cartInfo.getSkuName());
            orderItem.setSkuNum(cartInfo.getSkuNum());
            orderItem.setSkuPrice(cartInfo.getCartPrice());
            orderItem.setThumbImg(cartInfo.getImgUrl());
            orderItemList.add(orderItem);
        }
        //计算总金额
        BigDecimal totalAmount = new BigDecimal(0);
        for(OrderItem orderItem : orderItemList){
            totalAmount = totalAmount.add(orderItem.getSkuPrice().multiply(new BigDecimal(orderItem.getSkuNum())));
        }
        TradeVo tradeVo = new TradeVo();
        tradeVo.setTotalAmount(totalAmount);
        tradeVo.setOrderItemList(orderItemList);
        return tradeVo;
    }

    /**
     * 提交订单，生成订单明细
     * @param orderInfoDto
     * @return
     */
    @Override
    public Long submitOrder(OrderInfoDto orderInfoDto) {
        //数据校验
        List<OrderItem> orderItemList = orderInfoDto.getOrderItemList();
        if(CollectionUtils.isEmpty(orderItemList)){
            throw new GlobalException(ResultCodeEnum.DATA_ERROR);
        }
        for(OrderItem orderItem : orderItemList){
            ProductSku productSku = productFeignClient.getBySkuId(orderItem.getSkuId());
            if(null==productSku){
                throw new GlobalException(ResultCodeEnum.DATA_ERROR);
            }
            //校验库存
            if(orderItem.getSkuNum()>productSku.getStockNum()){
                throw new GlobalException(ResultCodeEnum.STOCK_LESS);
            }
        }

        //构建订单数据，保存订单信息，order_info
        UserInfo userInfo = AuthContextUtil.getUserInfo();
        OrderInfo orderInfo = new OrderInfo();
        //订单编号
        orderInfo.setOrderNo(String.valueOf(System.currentTimeMillis()));//当前系统时间作为编号
        //用户id
        orderInfo.setUserId(userInfo.getId());
        //用户昵称
        orderInfo.setNickName(userInfo.getNickName());
        //用户收获地址信息
        UserAddress userAddress = userFeignClient.getUserAddress(orderInfoDto.getUserAddressId());
        orderInfo.setReceiverName(userAddress.getName());
        orderInfo.setReceiverPhone(userAddress.getPhone());
        orderInfo.setReceiverTagName(userAddress.getTagName());
        orderInfo.setReceiverProvince(userAddress.getProvinceCode());
        orderInfo.setReceiverCity(userAddress.getCityCode());
        orderInfo.setReceiverDistrict(userAddress.getDistrictCode());
        orderInfo.setReceiverAddress(userAddress.getFullAddress());
        //订单金额
        BigDecimal totalAmount = new BigDecimal(0);
        for(OrderItem orderItem : orderItemList){
            totalAmount = totalAmount.add(orderItem.getSkuPrice().multiply(new BigDecimal(orderItem.getSkuNum())));
        }
        orderInfo.setTotalAmount(totalAmount);
        orderInfo.setOriginalTotalAmount(totalAmount);
        //优惠券
        orderInfo.setCouponAmount(new BigDecimal(0));
        //运费
        orderInfo.setFeightFee(orderInfoDto.getFeightFee());
        //支付方式，1微信，2支付宝
        orderInfo.setPayType(2);
        orderInfo.setOrderStatus(0);
        orderInfoMapper.save(orderInfo);
        //保存订单明细,订单项：order_item
        for(OrderItem orderItem : orderItemList){
            orderItem.setOrderId(orderInfo.getId());
            orderItemMapper.save(orderItem);
        }

        //记录日志,order_log
        OrderLog orderLog = new OrderLog();
        orderLog.setOrderId(orderInfo.getId());
        //订单状态
        orderLog.setProcessStatus(0);
        orderLog.setNote("提交订单");
        orderLogMapper.save(orderLog);
        //远程调用service-cart微服务接口清空购物车数据
        cartFeignClient.deleteChecked();

        //返回订单号
        return orderInfo.getId();
    }

    /**
     * 根据订单id获取订单信息
     * @param orderId
     * @return
     */
    @Override
    public OrderInfo getOrderInfo(Long orderId) {
        return orderInfoMapper.getById(orderId);
    }

    /**
     * 直接购买-生成订单信息
     * @param skuId
     * @return
     */
    @Override
    public TradeVo buy(Long skuId) {
        //获取用户id和购物车键名
        Long userId = AuthContextUtil.getUserInfo().getId();
        String cartKey = "user:cart:"+userId;
        //通过skuId获取商品单元信息
        ProductSku productSku = productFeignClient.getBySkuId(skuId);

        //生成订单列表（就一个列表项）
        List<OrderItem> orderItemList = new ArrayList<>();
        OrderItem orderItem = new OrderItem();
        orderItem.setSkuId(skuId);
        orderItem.setSkuName(productSku.getSkuName());
        orderItem.setSkuNum(1);
        orderItem.setSkuPrice(productSku.getSalePrice());
        orderItem.setThumbImg(productSku.getThumbImg());
        orderItemList.add(orderItem);

        // 计算总金额
        BigDecimal totalAmount = productSku.getSalePrice();
        TradeVo tradeVo = new TradeVo();
        tradeVo.setTotalAmount(totalAmount);
        tradeVo.setOrderItemList(orderItemList);

        // 返回
        return tradeVo;
    }

    /**
     * 获取订单分页列表
     * @param page
     * @param limit
     * @param orderStatus
     * @return
     */
    @Override
    public PageInfo<OrderInfo> list(Integer page, Integer limit, Integer orderStatus) {
        PageHelper.startPage(page,limit);
        //获取用户id和购物车键名
        Long userId = AuthContextUtil.getUserInfo().getId();
        List<OrderInfo> orderInfoList = orderInfoMapper.findUserPage(userId,orderStatus);
        for(OrderInfo orderInfo : orderInfoList){
            List<OrderItem> orderItemList = orderItemMapper.findByOrderId(orderInfo.getId());
            orderInfo.setOrderItemList(orderItemList);
        }
        PageInfo<OrderInfo> pageInfo = new PageInfo<>(orderInfoList);
        return pageInfo;
    }

    /**
     * 根据订单id获取订单信息
     * @return
     */
    @Override
    public OrderInfo getOrderInfoByOrderNo(String orderNo) {
        OrderInfo orderInfo = orderInfoMapper.getOrderInfoByOrderNo(orderNo);
        //订单项明细也要查询
        List<OrderItem> orderItem = orderItemMapper.findByOrderId(orderInfo.getId());
        orderInfo.setOrderItemList(orderItem);
        return orderInfo;
    }
}
