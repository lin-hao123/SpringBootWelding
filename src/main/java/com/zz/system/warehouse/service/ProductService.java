package com.zz.system.warehouse.service;

import com.zz.system.warehouse.entity.Product;
import org.springframework.data.domain.Page;

/**
 * created by linlx on 2020/1/15
 */
public interface ProductService {

    Product create(Product product);

    Page<Product> findByProductNameLike(String productName, int page, int size);

    Product findById(Long productId);

    void delete(Long productId);
}
