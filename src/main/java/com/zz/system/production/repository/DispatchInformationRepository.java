package com.zz.system.production.repository;

import com.zz.system.production.entity.DispatchInformation;
import com.zz.system.production.entity.EquipmentMaintenanceApplication;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * created by linlx on 2020/1/22
 */
public interface DispatchInformationRepository extends JpaRepository<DispatchInformation,Long> {

    Page<DispatchInformation> findByProductNameLike(String productName, Pageable pageable);
    
    @Modifying
   	@Query("update DispatchInformation di set "+
   	"di.productionId=CASE WHEN:#{#m.productionId} IS NULL THEN di.productionId ELSE:#{#m.productionId} END,"+
   	"di.taskVolume=CASE WHEN:#{#m.taskVolume} IS NULL THEN di.taskVolume ELSE:#{#m.taskVolume} END,"+
   	"di.startTime=CASE WHEN:#{#m.startTime} IS NULL THEN di.startTime ELSE:#{#m.startTime} END,"+
   	"di.finishTime=CASE WHEN:#{#m.finishTime} IS NULL THEN di.finishTime ELSE:#{#m.finishTime} END,"+
   	"di.productionInstruction=CASE WHEN:#{#m.productionInstruction} IS NULL THEN di.productionInstruction ELSE:#{#m.productionInstruction} END,"+
   	"di.production=CASE WHEN:#{#m.production} IS NULL THEN di.production ELSE:#{#m.production} END,"+
   	"di.deliveryTime=CASE WHEN:#{#m.deliveryTime} IS NULL THEN di.deliveryTime ELSE:#{#m.deliveryTime} END,"+
   	"di.productName=CASE WHEN:#{#m.productName} IS NULL THEN di.productName ELSE:#{#m.productName} END "+
   	"where di.dispatchId=:#{#m.dispatchId}")
   	public Integer updateDispatchInformation(@Param("m") DispatchInformation dispatchInformation);
}
