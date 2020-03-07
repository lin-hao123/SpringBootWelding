package com.zz.system.design.service;

import com.zz.system.design.entity.ProductionInstruction;
import org.springframework.data.domain.Page;

/**
 * created by linlx on 2020/1/20
 */
public interface ProductionInstructionService {

    Page<ProductionInstruction> findByProductionOrderLike(String productionOrder, int page, int size);

    ProductionInstruction findById(Long productionInstructionId);

    ProductionInstruction create(ProductionInstruction productionInstruction);

    void delete(Long productionInstructionId);
    
    Integer updateProductionInstruction(ProductionInstruction productionInstruction);

}
