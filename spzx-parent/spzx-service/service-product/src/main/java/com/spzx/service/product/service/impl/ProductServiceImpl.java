package com.spzx.service.product.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.spzx.model.dto.h5.ProductSkuDto;
import com.spzx.model.entity.product.Product;
import com.spzx.model.entity.product.ProductDetails;
import com.spzx.model.entity.product.ProductSku;
import com.spzx.model.vo.h5.ProductItemVo;
import com.spzx.service.product.mapper.ProductDetailsMapper;
import com.spzx.service.product.mapper.ProductMapper;
import com.spzx.service.product.mapper.ProductSkuMapper;
import com.spzx.service.product.service.ProductService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ClassName: ProductServiceImpl
 * Package: com.spzx.service.product.service.impl
 * Description:
 * Author: zhj
 * Create: 2024/4/19 - 9:12
 * Version: v1.0
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Resource
    ProductSkuMapper productSkuMapper;
    @Resource
    ProductMapper productMapper;
    @Resource
    ProductDetailsMapper productDetailsMapper;

    /**
     * 获取热销产品
     * @return
     */
    @Override
    public List<ProductSku> findSkuListBySale() {
        return productSkuMapper.findSkuListBySale();
    }

    /**
     * 分页查询产品列表
     * @return
     */
    @Override
    public PageInfo<ProductSku> findByPage(Integer page, Integer limit, ProductSkuDto productSkuDto) {
        PageHelper.startPage(page,limit);

        List<ProductSku> list = productSkuMapper.findByPage(productSkuDto);
        PageInfo<ProductSku> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    /**
     * 查询商品详情信息
     * @param skuId
     * @return
     */
    @Override
    public ProductItemVo getProductItemBySkuId(Long skuId) {
        //查询商品sku信息
        ProductSku productSku = productSkuMapper.findProductSku(skuId);
        //根据查询到的sku，获取productId，查询商品基本信息
        Product product = productMapper.getProductById(productSku.getProductId());
        //根据商品id，查询同一个商品下的的sku列表
        List<ProductSku> list = productSkuMapper.findSkuListByPId(productSku.getProductId());
        //根据商品id，查询详情
        ProductDetails productDetails = productDetailsMapper.findProductDetailsById(productSku.getProductId());

        //建立sku列表与skuid的关系
        Map<String,Object> map = new HashMap<>();
        for(ProductSku item :list){
            map.put(item.getSkuSpec(),item.getId());
        }

        ProductItemVo productItemVo = new ProductItemVo();
        productItemVo.setProductSku(productSku);
        productItemVo.setProduct(product);
        productItemVo.setDetailsImageUrlList(Arrays.asList(productDetails.getImageUrls().split(",")));
        productItemVo.setSliderUrlList(Arrays.asList(product.getSliderUrls().split(",")));
        productItemVo.setSpecValueList(JSON.parseArray(product.getSpecValue()));
        productItemVo.setSkuSpecValueMap(map);
        return productItemVo;
    }


    /**
     * 根据skuid获取商品sku信息
     * @param skuId
     * @return
     */
    @Override
    public ProductSku getBySkuId(Long skuId) {
        return productSkuMapper.findProductSku(skuId);
    }
}
