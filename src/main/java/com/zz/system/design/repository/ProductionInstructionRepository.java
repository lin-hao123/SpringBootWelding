package com.zz.system.design.repository;

import com.zz.system.design.entity.Customer;
import com.zz.system.design.entity.ProductionInstruction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * created by linlx on 2020/1/20
 */
public interface ProductionInstructionRepository extends JpaRepository<ProductionInstruction,Long> {


    Page<ProductionInstruction> findByProductionOrderLike(String productionOrder, Pageable pageable);
    
    @Modifying
	@Query("update ProductionInstruction pi set "+
	"pi.productionOrder=CASE WHEN:#{#m.productionOrder} IS NULL THEN pi.productionOrder ELSE:#{#m.productionOrder} END,"+
	"pi.weldingMethod=CASE WHEN:#{#m.weldingMethod} IS NULL THEN pi.weldingMethod ELSE:#{#m.weldingMethod} END,"+
	"pi.weldingEquipment=CASE WHEN:#{#m.weldingEquipment} IS NULL THEN pi.weldingEquipment ELSE:#{#m.weldingEquipment} END,"+
	"pi.processParameter=CASE WHEN:#{#m.processParameter} IS NULL THEN pi.processParameter ELSE:#{#m.processParameter} END,"+
	"pi.preWeldingTreatment=CASE WHEN:#{#m.preWeldingTreatment} IS NULL THEN pi.preWeldingTreatment ELSE:#{#m.preWeldingTreatment} END,"+
	"pi.postWeldProcessing=CASE WHEN:#{#m.postWeldProcessing} IS NULL THEN pi.postWeldProcessing ELSE:#{#m.postWeldProcessing} END "+
	"where pi.productionInstructionId=:#{#m.productionInstructionId}")
	public Integer updateProductionInstruction(@Param("m") ProductionInstruction productionInstruction);
}
