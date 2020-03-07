package com.zz.system.design.repository;

import com.zz.system.design.entity.Customer;
import com.zz.system.design.entity.ProductionPlan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * created by linlx on 2020/1/20
 */
public interface ProductionPlanRepository extends JpaRepository<ProductionPlan,Long> {

    Page<ProductionPlan> findByProductNameLike(String productionPlanName, Pageable pageable);
    
    @Modifying
   	@Query("update ProductionPlan pp set "+
   	"pp.specification=CASE WHEN:#{#m.specification} IS NULL THEN pp.specification ELSE:#{#m.specification} END,"+
   	"pp.productName=CASE WHEN:#{#m.productName} IS NULL THEN pp.productName ELSE:#{#m.productName} END,"+
   	"pp.productionOrder=CASE WHEN:#{#m.productionOrder} IS NULL THEN pp.productionOrder ELSE:#{#m.productionOrder} END,"+
   	"pp.num=CASE WHEN:#{#m.num} IS NULL THEN pp.num ELSE:#{#m.num} END,"+
   	"pp.deliveryDate=CASE WHEN:#{#m.deliveryDate} IS NULL THEN pp.deliveryDate ELSE:#{#m.deliveryDate} END,"+
   	"pp.updateTime=CASE WHEN:#{#m.updateTime} IS NULL THEN pp.updateTime ELSE:#{#m.updateTime} END,"+
   	"pp.createTime=CASE WHEN:#{#m.createTime} IS NULL THEN pp.createTime ELSE:#{#m.createTime} END "+
   	"where pp.productionPlanId=:#{#m.productionPlanId}")
   	public Integer updateProductionPlan(@Param("m") ProductionPlan productionPlan);
}
