package com.spzx.service.cart.controller;

import com.spzx.model.entity.h5.CartInfo;
import com.spzx.model.vo.common.Result;
import com.spzx.model.vo.common.ResultCodeEnum;
import com.spzx.service.cart.service.CartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ClassName: CardController
 * Package: com.spzx.service.cart
 * Description:
 * Author: zhj
 * Create: 2024/4/20 - 11:22
 * Version: v1.0
 */
@Tag(name = "购物车接口")
@RestController
@RequestMapping("api/order/cart")
public class CartController {
    @Autowired
    CartService cartService;

    /**
     * 添加商品至购物车
     * @param skuId
     * @param skuNum
     * @return
     */
    @Operation(summary = "添加购物车")
    @GetMapping("auth/addToCart/{skuId}/{skuNum}")
    public Result addToCart(@PathVariable(name = "skuId")Long skuId,
                            @PathVariable(name = "skuNum")Integer skuNum){
        cartService.addToCart(skuId,skuNum);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    /**
     * 查询购物车列表
     * @return
     */
    @Operation(summary = "查询购物车")
    @GetMapping("auth/cartList")
    public Result<List<CartInfo>> cartList() {
        List<CartInfo> list = cartService.getCartList();
        return Result.build(list,ResultCodeEnum.SUCCESS);
    }

    /**
     * 删除购物车商品
     * @param skuId
     * @return
     */
    @Operation(summary = "删除购物车商品")
    @DeleteMapping("auth/deleteCart/{skuId}")
    public Result deleteCart(@Parameter(name = "skuId", description = "商品skuId", required = true) @PathVariable("skuId") Long skuId) {
        cartService.deleteCart(skuId);
        return Result.build(null,ResultCodeEnum.SUCCESS);
    }

    /**
     * 更新购物车商品选中状态
     * @param skuId
     * @param isChecked
     * @return
     */
    @Operation(summary="更新购物车商品选中状态")
    @GetMapping("/auth/checkCart/{skuId}/{isChecked}")
    public Result checkCart(@Parameter(name = "skuId", description = "商品skuId", required = true) @PathVariable(value = "skuId") Long skuId,
                            @Parameter(name = "isChecked", description = "是否选中 1:选中 0:取消选中", required = true) @PathVariable(value = "isChecked") Integer isChecked) {
        cartService.checkCart(skuId,isChecked);
        return Result.build(null,ResultCodeEnum.SUCCESS);
    }

    /**
     * 全选全不选
     * @param isChecked
     * @return
     */
    @Operation(summary="更新购物车商品全部选中状态")
    @GetMapping("/auth/allCheckCart/{isChecked}")
    public Result allCheckCart(@Parameter(name = "isChecked", description = "是否选中 1:选中 0:取消选中", required = true) @PathVariable(value = "isChecked") Integer isChecked){
        cartService.allCheckCart(isChecked);
        return Result.build(null,ResultCodeEnum.SUCCESS);
    }

    /**
     * 清空购物车
     * @return
     */
    @Operation(summary="清空购物车")
    @GetMapping("/auth/clearCart")
    public Result clearCart() {
        cartService.clearCart();
        return Result.build(null,ResultCodeEnum.SUCCESS);
    }


    /**
     * 获取选中的购物车列表
     * @return
     */
    @Operation(summary="选中的购物项列表")
    @GetMapping(value = "/auth/getAllChecked")
    public List<CartInfo> getAllChecked() {
        List<CartInfo> list = cartService.getAllChecked();
        return list;
    }

    /**
     * 订单提交后，清除已经选中的购物车信息
     */
    @GetMapping(value = "/auth/deleteChecked")
    public Result deleteChecked() {
        cartService.deleteChecked();
        return Result.build(null,ResultCodeEnum.SUCCESS);
    }
}
