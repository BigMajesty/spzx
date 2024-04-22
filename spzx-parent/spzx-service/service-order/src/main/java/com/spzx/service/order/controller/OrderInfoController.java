package com.spzx.service.order.controller;

import com.github.pagehelper.PageInfo;
import com.spzx.model.entity.order.OrderInfo;
import com.spzx.service.order.service.OrderInfoService;
import com.spzx.model.dto.h5.OrderInfoDto;
import com.spzx.model.vo.common.Result;
import com.spzx.model.vo.common.ResultCodeEnum;
import com.spzx.model.vo.h5.TradeVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * ClassName: OrderInfoController
 * Package: com.spax.service.order.controller
 * Description:
 * Author: zhj
 * Create: 2024/4/20 - 17:15
 * Version: v1.0
 */
@Tag(name = "订单管理")
@RestController
@RequestMapping(value="/api/order/orderInfo")
public class OrderInfoController {
    @Autowired
    private OrderInfoService orderInfoService;

    /**
     * 生成订单数据并返回
     * @return
     */
    @Operation(summary = "确认下单")
    @GetMapping("auth/trade")
    public Result trade(){
        TradeVo tradeVo = orderInfoService.trade();
        return Result.build(tradeVo, ResultCodeEnum.SUCCESS);
    }

    /**
     * 点击立即购买，生成订单信息
     * @param skuId
     * @return
     */
    @Operation(summary = "立即购买")
    @GetMapping("auth/buy/{skuId}")
    public Result<TradeVo> buy(@Parameter(name = "skuId", description = "商品skuId", required = true)
                                   @PathVariable Long skuId) {
        TradeVo tradeVo = orderInfoService.buy(skuId);
        return Result.build(tradeVo,ResultCodeEnum.SUCCESS);
    }

    /**
     * 提交订单，生成订单明细存入数据库
     * @param orderInfoDto
     * @return
     */
    @Operation(summary = "提交订单")
    @PostMapping("auth/submitOrder")
    public Result<Long> submitOrder(@Parameter(name = "orderInfoDto", description = "请求参数实体类", required = true)
                                        @RequestBody OrderInfoDto orderInfoDto) {
        Long orderId = orderInfoService.submitOrder(orderInfoDto);
        return Result.build(orderId,ResultCodeEnum.SUCCESS);
    }

    /**
     * 根据订单id获取订单信息
     * @param orderId
     * @return
     */
    @Operation(summary = "获取订单信息")
    @GetMapping("auth/{orderId}")
    public Result<OrderInfo> getOrderInfo(@Parameter(name = "orderId", description = "订单id", required = true) @PathVariable Long orderId) {
        OrderInfo orderInfo = orderInfoService.getOrderInfo(orderId);
        return Result.build(orderInfo, ResultCodeEnum.SUCCESS);
    }

    /**
     * 获取订单分页列表
     * @param page
     * @param limit
     * @param orderStatus
     * @return
     */
    @Operation(summary = "获取订单分页列表")
    @GetMapping("auth/{page}/{limit}")
    public Result<PageInfo<OrderInfo>> list(@PathVariable(name = "page")Integer page,
                                            @PathVariable(name = "limit")Integer limit,
                                            @RequestParam(required = false, defaultValue = "") Integer orderStatus){
        PageInfo<OrderInfo> pageInfo = orderInfoService.list(page,limit,orderStatus);
        return Result.build(pageInfo,ResultCodeEnum.SUCCESS);
    }

    /**
     * 根据订单no获取订单信息
     * @param orderNo
     * @return
     */
    @Operation(summary = "获取订单信息")
    @GetMapping("auth/getOrderInfoByOrderNo/{orderNo}")
    public Result<OrderInfo> getOrderInfoByOrderNo(@Parameter(name = "orderId", description = "订单id", required = true)
                                                       @PathVariable String orderNo) {
        OrderInfo orderInfo = orderInfoService.getOrderInfoByOrderNo(orderNo);
        return Result.build(orderInfo,ResultCodeEnum.SUCCESS);
    }
}
