package com.zz.system.warehouse.service;

import com.zz.system.warehouse.entity.Supplier;
import org.springframework.data.domain.Page;

/**
 * created by linlx on 2020/1/20
 */
public interface SupplierService {

    Page<Supplier> findBySupplierNameLike(String supplierName,int page,int size);

    Supplier findById(Long supplierId);

    Supplier create(Supplier supplier);

    void delete(Long supplierId);

    Integer updateSupplier(Supplier supplier);
}
