package com.spzx.service.pay.service.impl;

import com.spzx.feign.order.OrderFeignClient;
import com.spzx.model.entity.order.OrderInfo;
import com.spzx.model.entity.order.OrderItem;
import com.spzx.model.entity.pay.PaymentInfo;
import com.spzx.model.vo.common.Result;
import com.spzx.service.pay.mapper.PaymentInfoMapper;
import com.spzx.service.pay.service.PaymentInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ClassName: PaymentInfoServiceImpl
 * Package: com.spzx.service.pay.service.impl
 * Description:
 * Author: zhj
 * Create: 2024/4/21 - 9:08
 * Version: v1.0
 */
@Service
public class PaymentInfoServiceImpl implements PaymentInfoService {
    @Autowired
    PaymentInfoMapper paymentInfoMapper;
    @Autowired
    OrderFeignClient orderFeignClient;

    /**
     * 构建PaymentInfo数据
     * @param orderNo
     * @return
     */
    @Override
    public PaymentInfo savePaymentInfo(String orderNo) {
        //根据订单号查询是否已经存在PaymentInfo数据
        PaymentInfo paymentInfo = paymentInfoMapper.getPaymentInfo(orderNo);
        //如果不存在，进行创建，存在则直接返回
        if(paymentInfo==null){
            OrderInfo orderInfo = orderFeignClient.getOrderInfoByOrderNo(orderNo).getData();
            paymentInfo = new PaymentInfo();
            paymentInfo.setUserId(orderInfo.getUserId());
            paymentInfo.setPayType(orderInfo.getPayType());
            //交易内容：sku名
            String content = "";
            for(OrderItem item : orderInfo.getOrderItemList()) {
                content += item.getSkuName() + " ";
            }
            paymentInfo.setContent(content);
            paymentInfo.setAmount(orderInfo.getTotalAmount());
            paymentInfo.setOrderNo(orderNo);
            //支付状态：未支付
            paymentInfo.setPaymentStatus(0);
            paymentInfoMapper.save(paymentInfo);
        }
        return paymentInfo;
    }
}
