package com.zz.system.warehouse.repository;

import com.zz.system.quality.entity.MaterialInspection;
import com.zz.system.warehouse.entity.DeliveryList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;



/**
 * created by linlx on 2020/1/19
 */
public interface DeliveryListRepository extends JpaRepository<DeliveryList,Long> {

    Page<DeliveryList> findByProductIdLike(String productId, Pageable pageable);
    
    @Modifying
   	@Query("update DeliveryList dl set "+
   	"dl.weldingProducts=CASE WHEN:#{#m.weldingProducts} IS NULL THEN dl.weldingProducts ELSE:#{#m.weldingProducts} END,"+
   	"dl.productId=CASE WHEN:#{#m.productId} IS NULL THEN dl.productId ELSE:#{#m.productId} END, "+
   	"dl.warehouseId=CASE WHEN:#{#m.warehouseId} IS NULL THEN dl.warehouseId ELSE:#{#m.warehouseId} END, "+
   	"dl.customerId=CASE WHEN:#{#m.customerId} IS NULL THEN dl.customerId ELSE:#{#m.customerId} END, "+
   	"dl.num=CASE WHEN:#{#m.num} IS NULL THEN dl.num ELSE:#{#m.num} END, "+
   	"dl.outTime=CASE WHEN:#{#m.outTime} IS NULL THEN dl.outTime ELSE:#{#m.outTime} END "+
   	"where dl.deliveryListId=:#{#m.deliveryListId}")
   	public Integer updateDeliveryList(@Param("m") DeliveryList deliveryList);
}
