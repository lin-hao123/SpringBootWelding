package com.zz.system.design.service;

import com.zz.system.design.entity.ProductionPlan;
import org.springframework.data.domain.Page;

/**
 * created by linlx on 2020/1/20
 */
public interface ProductionPlanService {

    Page<ProductionPlan> findByProductNameLike(String productName, int page, int size);

    ProductionPlan findById(Long productionPlanId);

    ProductionPlan create(ProductionPlan productionPlan);

    void delete(Long productionPlanId);
    
    Integer updateProductionPlan(ProductionPlan productionPlan);
}
