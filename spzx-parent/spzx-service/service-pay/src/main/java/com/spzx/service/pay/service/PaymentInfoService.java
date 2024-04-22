package com.spzx.service.pay.service;

import com.spzx.model.entity.pay.PaymentInfo;

/**
 * ClassName: PaymentInfoService
 * Package: com.spzx.service.pay.service
 * Description:
 * Author: zhj
 * Create: 2024/4/21 - 9:08
 * Version: v1.0
 */
public interface PaymentInfoService {
    public PaymentInfo savePaymentInfo(String orderNo);
}
