package com.zz.system.design.repository;

import com.zz.system.design.entity.ProductionInstruction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * created by linlx on 2020/1/20
 */
public interface ProductionInstructionRepository extends JpaRepository<ProductionInstruction,Long> {


    Page<ProductionInstruction> findByProductionOrderLike(String productionOrder, Pageable pageable);
}
