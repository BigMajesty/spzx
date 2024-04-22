package com.spzx.manager.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.spzx.manager.mapper.ProductDetailsMapper;
import com.spzx.manager.mapper.ProductMapper;
import com.spzx.manager.mapper.ProductSkuMapper;
import com.spzx.manager.service.ProductService;
import com.spzx.model.dto.product.ProductDto;
import com.spzx.model.entity.product.Product;
import com.spzx.model.entity.product.ProductDetails;
import com.spzx.model.entity.product.ProductSku;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName: ProductServiceImpl
 * Package: com.spzx.manager.service.impl
 * Description:
 * Author: zhj
 * Create: 2024/4/14 - 19:07
 * Version: v1.0
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductMapper productMapper;
    @Autowired
    ProductSkuMapper productSkuMapper;
    @Autowired
    ProductDetailsMapper productDetailsMapper;
    /**
     * 查询商品列表
     *
     * @param page
     * @param limit
     * @param productDto
     * @return
     */
    @Override
    public PageInfo<Product> findByPage(Integer page, Integer limit, ProductDto productDto) {
        PageHelper.startPage(page,limit);
        List<Product> list = productMapper.findByPage(productDto);
        PageInfo<Product> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    /**
     * 添加商品
     * @param product
     */
    @Override
    public void save(Product product) {
        //保存商品基本信息到product表
            //设置状态值与审核值
        product.setStatus(0);
        product.setAuditStatus(0);
        productMapper.save(product);
        //获取商品sku列表，保存sku信息，product_sku表
        List<ProductSku> productSkuList = product.getProductSkuList();
        for(int i=0;i<productSkuList.size();i++){
            // 获取ProductSku对象
            ProductSku productSku = productSkuList.get(i);
            productSku.setSkuCode(product.getId() + "_" + i);       // 构建skuCode

            productSku.setProductId(product.getId());               // 设置商品id
            productSku.setSkuName(product.getName() + productSku.getSkuSpec());
            productSku.setSaleNum(0);                               // 设置销量
            productSku.setStatus(0);
            productSkuMapper.save(productSku);                    // 保存数据

        }

        // 保存商品详情数据
        ProductDetails productDetails = new ProductDetails();
        productDetails.setProductId(product.getId());
        productDetails.setImageUrls(product.getDetailsImageUrls());
        productDetailsMapper.save(productDetails);
    }

    @Override
    public Product getById(Long id) {
        // 根据id查询商品基本信息
        Product product = productMapper.findProductById(id);
        //根据id查询商品sku信息
        List<ProductSku> list = productSkuMapper.findProductSkuById(id);
        //根据id查询商品详情数据
        ProductDetails productDetails = productDetailsMapper.findProductDetailsById(id);
        product.setProductSkuList(list);
        product.setDetailsImageUrls(productDetails.getImageUrls());
        return product;
    }

    /**
     * 修改商品信息
     * @param product
     */
    @Override
    public void updateById(Product product) {
        // 修改商品基本数据
        productMapper.updateById(product);

        // 修改商品的sku数据
        List<ProductSku> productSkuList = product.getProductSkuList();
        productSkuList.forEach(productSku -> {
            productSkuMapper.updateById(productSku);
        });

        // 修改商品的详情数据
        ProductDetails productDetails = productDetailsMapper.findProductDetailsById(product.getId());
        productDetails.setImageUrls(product.getDetailsImageUrls());
        productDetailsMapper.updateById(productDetails);
    }

    /**
     * 删除商品信息
     * @param id
     */
    @Override
    public void deleteById(Long id) {
        productMapper.deleteById(id);                   // 根据id删除商品基本数据
        productSkuMapper.deleteByProductId(id);         // 根据商品id删除商品的sku数据
        productDetailsMapper.deleteByProductId(id);     // 根据商品的id删除商品的详情数据
    }

    /**
     * 商品审核，auditStatus:1审核通过，0 审核不通过
     * @param id
     * @param auditStatus
     */
    @Override
    public void updateAuditStatus(Long id, Integer auditStatus) {
        Product product = new Product();
        product.setId(id);
        if(auditStatus==1){
            product.setAuditStatus(1);
            product.setAuditMessage("审核通过");
        }else{
            product.setAuditStatus(0);
            product.setAuditMessage("审核不通过");
        }
        productMapper.updateById(product);
    }

    /**
     * 商品上下架
     * @param id
     * @param status
     */
    @Override
    public void updateStatus(Long id, Integer status) {
        Product product = new Product();
        product.setId(id);
        if(status == 1) {
            product.setStatus(1);
        } else {
            product.setStatus(-1);
        }
        productMapper.updateById(product);
    }
}
