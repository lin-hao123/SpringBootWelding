package com.zz.system.warehouse.repository;

import com.zz.system.warehouse.entity.Product;
import com.zz.system.warehouse.entity.Supplier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


/**
 * created by linlx on 2020/1/20
 */
public interface SupplierRepository extends JpaRepository<Supplier, Long> {

    Page<Supplier> findBySupplierNameLike(String supplierName, Pageable pageable);

    @Modifying
    @Query("update Supplier s set "+
            "s.supplierName=CASE WHEN:#{#m.supplierName} IS NULL THEN s.supplierName ELSE:#{#m.supplierName} END,"+
            "s.material=CASE WHEN:#{#m.material} IS NULL THEN s.material ELSE:#{#m.material} END,"+
            "s.responsible=CASE WHEN:#{#m.responsible} IS NULL THEN s.responsible ELSE:#{#m.responsible} END,"+
            "s.supplierTel=CASE WHEN:#{#m.supplierTel} IS NULL THEN s.supplierTel ELSE:#{#m.supplierTel} END,"+
            "s.address=CASE WHEN:#{#m.address} IS NULL THEN s.address ELSE:#{#m.address} END,"+
            "s.note=CASE WHEN:#{#m.note} IS NULL THEN s.note ELSE:#{#m.note} END "+
            "where s.supplierId=:#{#m.supplierId}")
    public Integer updateSupplier(@Param("m") Supplier supplier);


}
