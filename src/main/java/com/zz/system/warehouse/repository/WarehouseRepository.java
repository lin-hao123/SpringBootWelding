package com.zz.system.warehouse.repository;

import com.zz.system.warehouse.entity.Supplier;
import com.zz.system.warehouse.entity.Warehouse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


/**
 * created by linlx on 2020/1/20
 */
public interface WarehouseRepository extends JpaRepository<Warehouse,Long> {

    Page<Warehouse> findByWarehouseNameLike(String WarehouseName, Pageable pageable);

    Warehouse findByWarehouseId(Long warehouseId);


    @Modifying
    @Query("update Warehouse w set "+
            "w.warehouseName=CASE WHEN:#{#m.warehouseName} IS NULL THEN w.warehouseName ELSE:#{#m.warehouseName} END,"+
            "w.type=CASE WHEN:#{#m.type} IS NULL THEN w.type ELSE:#{#m.type} END,"+
            "w.responsible=CASE WHEN:#{#m.responsible} IS NULL THEN w.responsible ELSE:#{#m.responsible} END,"+
            "w.warehouseTel=CASE WHEN:#{#m.warehouseTel} IS NULL THEN w.warehouseTel ELSE:#{#m.warehouseTel} END,"+
            "w.address=CASE WHEN:#{#m.address} IS NULL THEN w.address ELSE:#{#m.address} END "+
            "where w.warehouseId=:#{#m.warehouseId}")
    public Integer updateWarehouse(@Param("m") Warehouse warehouse);
}
