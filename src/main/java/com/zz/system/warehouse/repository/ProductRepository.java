package com.zz.system.warehouse.repository;

import com.zz.system.warehouse.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * created by linlx on 2020/1/15
 */
public interface ProductRepository extends JpaRepository<Product,Long> {

    Page<Product> findByProductNameLike(String productName, Pageable pageable);
}
