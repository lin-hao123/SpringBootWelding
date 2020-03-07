package com.zz.system.production.repository;

import com.zz.system.production.entity.Equipment;
import com.zz.system.production.entity.WelderInformation;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * created by linlx on 2020/2/25
 */
public interface EquipmentRepository extends JpaRepository<Equipment,Long> {

    Page<Equipment> findByEquipmentNameLike(String equipmentName, Pageable pageable);
    
    @Modifying
   	@Query("update Equipment ep set "+
   	"ep.equipmentName=CASE WHEN:#{#m.equipmentName} IS NULL THEN ep.equipmentName ELSE:#{#m.equipmentName} END,"+
   	"ep.equipmentLocation=CASE WHEN:#{#m.equipmentLocation} IS NULL THEN ep.equipmentLocation ELSE:#{#m.equipmentLocation} END,"+
   	"ep.weldingType=CASE WHEN:#{#m.weldingType} IS NULL THEN ep.weldingType ELSE:#{#m.weldingType} END,"+
   	"ep.useStatus=CASE WHEN:#{#m.useStatus} IS NULL THEN ep.useStatus ELSE:#{#m.useStatus} END,"+
   	"ep.weldingVoltage=CASE WHEN:#{#m.weldingVoltage} IS NULL THEN ep.weldingVoltage ELSE:#{#m.weldingVoltage} END,"+
   	"ep.weldingCurrent=CASE WHEN:#{#m.weldingCurrent} IS NULL THEN ep.weldingCurrent ELSE:#{#m.weldingCurrent} END,"+
   	"ep.weldingSpeed=CASE WHEN:#{#m.weldingSpeed} IS NULL THEN ep.weldingSpeed ELSE:#{#m.weldingSpeed} END "+
   	"where ep.equipmentId=:#{#m.equipmentId}")
   	public Integer updateEquipment(@Param("m") Equipment equipment); 
}
