package com.zz.system.quality.repository;

import com.zz.system.production.entity.DispatchInformation;
import com.zz.system.quality.entity.MaterialInspection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * created by linlx on 2020/2/24
 */
public interface MaterialInspectionRepository extends JpaRepository<MaterialInspection,Long> {

    Page<MaterialInspection> findByInspectionStaffLike(String inspectionStaff, Pageable pageable);
    
    @Modifying
   	@Query("update MaterialInspection mi set "+
   	"mi.weldingConsumablesId=CASE WHEN:#{#m.weldingConsumablesId} IS NULL THEN mi.weldingConsumablesId ELSE:#{#m.weldingConsumablesId} END,"+
   	"mi.detectionQuantity=CASE WHEN:#{#m.detectionQuantity} IS NULL THEN mi.detectionQuantity ELSE:#{#m.detectionQuantity} END,"+
   	"mi.qualifiedQuantity=CASE WHEN:#{#m.qualifiedQuantity} IS NULL THEN mi.qualifiedQuantity ELSE:#{#m.qualifiedQuantity} END,"+
   	"mi.inspectionStaff=CASE WHEN:#{#m.inspectionStaff} IS NULL THEN mi.inspectionStaff ELSE:#{#m.inspectionStaff} END,"+
   	"mi.note=CASE WHEN:#{#m.note} IS NULL THEN mi.note ELSE:#{#m.note} END,"+
   	"mi.checkedTime=CASE WHEN:#{#m.checkedTime} IS NULL THEN mi.checkedTime ELSE:#{#m.checkedTime} END,"+
   	"mi.weldingConsumables=CASE WHEN:#{#m.weldingConsumables} IS NULL THEN mi.weldingConsumables ELSE:#{#m.weldingConsumables} END "+
   	"where mi.materialInspectionId=:#{#m.materialInspectionId}")
   	public Integer updateMaterialInspection(@Param("m") MaterialInspection materialInspection);
}
