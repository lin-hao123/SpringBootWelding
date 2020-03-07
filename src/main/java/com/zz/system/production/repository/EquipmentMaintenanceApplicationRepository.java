package com.zz.system.production.repository;

import com.zz.system.production.entity.Equipment;
import com.zz.system.production.entity.EquipmentMaintenanceApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * created by linlx on 2020/1/21
 */
public interface EquipmentMaintenanceApplicationRepository extends JpaRepository<EquipmentMaintenanceApplication,Long> {

    Page<EquipmentMaintenanceApplication> findByDeviceNameLike(String deviceName, Pageable pageable);
    
    @Modifying
   	@Query("update EquipmentMaintenanceApplication ema set "+
   	"ema.deviceId=CASE WHEN:#{#m.deviceId} IS NULL THEN ema.deviceId ELSE:#{#m.deviceId} END,"+
   	"ema.deviceName=CASE WHEN:#{#m.deviceName} IS NULL THEN ema.deviceName ELSE:#{#m.deviceName} END,"+
   	"ema.responsible=CASE WHEN:#{#m.responsible} IS NULL THEN ema.responsible ELSE:#{#m.responsible} END,"+
   	"ema.applyReason=CASE WHEN:#{#m.applyReason} IS NULL THEN ema.applyReason ELSE:#{#m.applyReason} END,"+
   	"ema.applyTime=CASE WHEN:#{#m.applyTime} IS NULL THEN ema.applyTime ELSE:#{#m.applyTime} END,"+
   	"ema.status=CASE WHEN:#{#m.status} IS NULL THEN ema.status ELSE:#{#m.status} END "+
   	"where ema.equipmentMaintenanceApplicationId=:#{#m.equipmentMaintenanceApplicationId}")
   	public Integer updateEquipmentMaintenanceApplication(@Param("m") EquipmentMaintenanceApplication equipmentMaintenanceApplication); 
}
