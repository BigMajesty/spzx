package com.spzx.service.pay.mapper;

import com.spzx.model.entity.pay.PaymentInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * ClassName: PaymentInfoMapper
 * Package: com.spzx.service.pay.mapper
 * Description:
 * Author: zhj
 * Create: 2024/4/21 - 9:09
 * Version: v1.0
 */
@Mapper
public interface PaymentInfoMapper {
    //根据订单流水号，查询PaymentInfo
    PaymentInfo getPaymentInfo(String orderNo);

    //保存paymentInfo信息至数据库
    void save(PaymentInfo paymentInfo);
}
