package com.zz.system.warehouse.repository;

import com.zz.system.warehouse.entity.Warehouse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * created by linlx on 2020/1/20
 */
public interface WarehouseRepository extends JpaRepository<Warehouse,Long> {

    Page<Warehouse> findByWarehouseNameLike(String WarehouseName, Pageable pageable);

    Warehouse findByWarehouseId(Long warehouseId);
}
