package com.zz.system.warehouse.repository;

import com.zz.system.warehouse.entity.Supplier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * created by linlx on 2020/1/20
 */
public interface SupplierRepository extends JpaRepository<Supplier, Long> {

    Page<Supplier> findBySupplierNameLike(String supplierName, Pageable pageable);


}
