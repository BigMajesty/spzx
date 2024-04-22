package com.spzx.feign.product;

import com.spzx.model.entity.product.ProductSku;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * ClassName: ProductFeignClient
 * Package: com.spzx.serverclient
 * Description:
 * Author: zhj
 * Create: 2024/4/20 - 11:03
 * Version: v1.0
 */
@FeignClient(value = "service-product")
public interface ProductFeignClient {
    @GetMapping("/api/product/getBySkuId/{skuId}")
    public abstract ProductSku getBySkuId(@PathVariable Long skuId);
}
