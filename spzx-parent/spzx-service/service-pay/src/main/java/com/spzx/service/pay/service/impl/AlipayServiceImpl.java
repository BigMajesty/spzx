package com.spzx.service.pay.service.impl;

import cn.hutool.log.Log;
import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.alipay.api.response.AlipayTradeWapPayResponse;
import com.spzx.common.exception.GlobalException;
import com.spzx.feign.order.OrderFeignClient;
import com.spzx.model.entity.order.OrderInfo;
import com.spzx.model.entity.pay.PaymentInfo;
import com.spzx.model.vo.common.Result;
import com.spzx.model.vo.common.ResultCodeEnum;
import com.spzx.service.pay.mapper.AlipayMapper;
import com.spzx.service.pay.service.AlipayService;
import com.spzx.service.pay.service.PaymentInfoService;
import com.spzx.service.pay.utils.AlipayProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;

/**
 * ClassName: AlipayServiceImpl
 * Package: com.spzx.service.pay.service.impl
 * Description:
 * Author: zhj
 * Create: 2024/4/21 - 8:58
 * Version: v1.0
 */
@Service
public class AlipayServiceImpl implements AlipayService {

    @Autowired
    AlipayMapper alipayMapper;
    @Autowired
    PaymentInfoService paymentInfoService;
    @Autowired
    AlipayProperties alipayProperties;
    @Autowired
    AlipayClient alipayClient;
    /**
     * 支付宝下单
     * @param orderNo
     * @return
     */
    @Override
    public String submitAlipay(String orderNo) {
         //保存支付记录
        PaymentInfo paymentInfo = paymentInfoService.savePaymentInfo(orderNo);
        //创建API对应的request
        AlipayTradeWapPayRequest alipayRequest = new AlipayTradeWapPayRequest();

        // 同步回调
        alipayRequest.setReturnUrl(alipayProperties.getReturnPaymentUrl());

        // 异步回调
        alipayRequest.setNotifyUrl(alipayProperties.getNotifyPaymentUrl());

        // 准备请求参数 ，声明一个map 集合
        HashMap<String, Object> map = new HashMap<>();
        map.put("out_trade_no",paymentInfo.getOrderNo());
        map.put("product_code","QUICK_WAP_WAY");
        //map.put("total_amount",paymentInfo.getAmount());
        map.put("total_amount",new BigDecimal("0.01"));
        map.put("subject",paymentInfo.getContent());
        alipayRequest.setBizContent(JSON.toJSONString(map));

        // 发送请求
        AlipayTradeWapPayResponse response = null;
        try {
            response = alipayClient.pageExecute(alipayRequest);
            if(response.isSuccess()){
                return response.getBody();
            }
        } catch (AlipayApiException e) {
            throw new RuntimeException(e);
        }
        if(response.isSuccess()){
            System.out.println("调用成功");
            return response.getBody();
        } else {
            System.out.println("调用失败");
            throw new GlobalException(ResultCodeEnum.DATA_ERROR);
        }

    }
}
