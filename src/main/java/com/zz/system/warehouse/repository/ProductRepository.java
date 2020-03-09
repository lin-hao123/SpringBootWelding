package com.zz.system.warehouse.repository;

import com.zz.system.warehouse.entity.MaterialRequestion;
import com.zz.system.warehouse.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * created by linlx on 2020/1/15
 */
public interface ProductRepository extends JpaRepository<Product,Long> {

    Page<Product> findByProductNameLike(String productName, Pageable pageable);

    @Modifying
    @Query("update Product p set "+
            "p.productName=CASE WHEN:#{#m.productName} IS NULL THEN p.productName ELSE:#{#m.productName} END,"+
            "p.num=CASE WHEN:#{#m.num} IS NULL THEN p.num ELSE:#{#m.num} END,"+
            "p.warehouse=CASE WHEN:#{#m.warehouse} IS NULL THEN p.warehouse ELSE:#{#m.warehouse} END,"+
            "p.location=CASE WHEN:#{#m.location} IS NULL THEN p.location ELSE:#{#m.location} END,"+
            "p.customer=CASE WHEN:#{#m.customer} IS NULL THEN p.customer ELSE:#{#m.customer} END,"+
            "p.status=CASE WHEN:#{#m.status} IS NULL THEN p.status ELSE:#{#m.status} END,"+
            "p.entryListId=CASE WHEN:#{#m.entryListId} IS NULL THEN p.entryListId ELSE:#{#m.entryListId} END,"+
            "p.finishedTime=CASE WHEN:#{#m.finishedTime} IS NULL THEN p.finishedTime ELSE:#{#m.finishedTime} END "+
            "where p.productId=:#{#m.productId}")
    public Integer updateProduct(@Param("m") Product product);

    void deleteByProductId(String productId);

    Product findByProductId(String productId);
}
