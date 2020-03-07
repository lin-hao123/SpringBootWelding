package com.zz.system.design.repository;

import com.zz.system.design.entity.ProductionPlan;
import com.zz.system.design.entity.TaskInstruction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * created by linlx on 2020/1/21
 */
public interface TaskInstructionRepository extends JpaRepository<TaskInstruction,Long> {

    Page<TaskInstruction> findByProductionOrderLike(String productionOrder, Pageable pageable);
    
    @Modifying
   	@Query("update TaskInstruction ti set "+
   	"ti.productionOrder=CASE WHEN:#{#m.productionOrder} IS NULL THEN ti.productionOrder ELSE:#{#m.productionOrder} END,"+
   	"ti.productName=CASE WHEN:#{#m.productName} IS NULL THEN ti.productName ELSE:#{#m.productName} END,"+
   	"ti.productionDepartment=CASE WHEN:#{#m.productionDepartment} IS NULL THEN ti.productionDepartment ELSE:#{#m.productionDepartment} END,"+
   	"ti.num=CASE WHEN:#{#m.num} IS NULL THEN ti.num ELSE:#{#m.num} END,"+
   	"ti.weldingMaterialId=CASE WHEN:#{#m.weldingMaterialId} IS NULL THEN ti.weldingMaterialId ELSE:#{#m.weldingMaterialId} END,"+
   	"ti.weldingMaterialName=CASE WHEN:#{#m.weldingMaterialName} IS NULL THEN ti.weldingMaterialName ELSE:#{#m.weldingMaterialName} END,"+
   	"ti.weldingMaterialNum=CASE WHEN:#{#m.weldingMaterialNum} IS NULL THEN ti.weldingMaterialNum ELSE:#{#m.weldingMaterialNum} END,"+
   	"ti.currentInventory=CASE WHEN:#{#m.currentInventory} IS NULL THEN ti.currentInventory ELSE:#{#m.currentInventory} END "+
   	"where ti.taskInstructionId=:#{#m.taskInstructionId}")
   	public Integer updateTaskInstruction(@Param("m") TaskInstruction taskInstruction);
}
