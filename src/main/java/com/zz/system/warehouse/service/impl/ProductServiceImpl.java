package com.zz.system.warehouse.service.impl;

import com.zz.system.warehouse.service.ProductService;
import com.zz.system.warehouse.entity.Product;
import com.zz.system.warehouse.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

/**
 * created by linlx on 2020/1/15
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Resource
    ProductRepository productRepository;

    @Override
    public Product create(Product product) {
        product.setCreateTime(new Date());
        return productRepository.save(product);
    }

    @Override
    public Page<Product> findByProductNameLike(String productName, int page, int size) {
        Sort sort=new Sort(Sort.Direction.DESC,"createTime");
        Pageable pageable= PageRequest.of(page,size,sort);
        if(productName==null||productName==""){
            productName="%%";
        }else {
            productName="%"+productName+"%";
        }
        return productRepository.findByProductNameLike(productName,pageable);
    }

    @Override
    public Product findById(Long productId) {
        return productRepository.findById(productId).get();
    }

    @Override
    @Transactional(value = "transactionManager", rollbackFor = {Exception.class}, readOnly = false)
    public void delete(Long productId) {
        productRepository.deleteById(productId);
    }


}
