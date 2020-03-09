package com.zz.system.warehouse.service;

import com.zz.system.warehouse.entity.Warehouse;
import org.springframework.data.domain.Page;

/**
 * created by linlx on 2020/1/20
 */
public interface WarehouseService {

    Page<Warehouse> findByWarehouseNameLike(String warehouseName,int page,int size);

    Warehouse create(Warehouse warehouse);

    Warehouse findById(Long warehouseId);

    void delete(Long warehouseId);

    Integer updateWarehouse(Warehouse warehouse);
}
