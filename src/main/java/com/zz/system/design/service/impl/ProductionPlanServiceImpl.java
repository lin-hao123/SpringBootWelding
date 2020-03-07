package com.zz.system.design.service.impl;

import com.zz.system.design.entity.Customer;
import com.zz.system.design.entity.ProductionPlan;
import com.zz.system.design.repository.ProductionPlanRepository;
import com.zz.system.design.service.ProductionPlanService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

/**
 * created by linlx on 2020/1/20
 */

@Service
public class ProductionPlanServiceImpl implements ProductionPlanService {

    @Resource
    ProductionPlanRepository productionPlanRepository;

    @Override
    public Page<ProductionPlan> findByProductNameLike(String productName, int page, int size) {
        Sort sort=new Sort(Sort.Direction.DESC,"createTime");
        Pageable pageable= PageRequest.of(page,size,sort);
        if(productName==null||productName==""){
            productName="%%";
        }else {
            productName="%"+productName+"%";
        }
        return productionPlanRepository.findByProductNameLike(productName,pageable);
    }

    @Override
    public ProductionPlan findById(Long productionPlanId) {
        return productionPlanRepository.findById(productionPlanId).get();
    }

    @Override
    public ProductionPlan create(ProductionPlan productionPlan) {
        productionPlan.setCreateTime(new Date());
        productionPlan.setUpdateTime(new Date());
        return productionPlanRepository.save(productionPlan);
    }

    @Override
    @Transactional(value = "transactionManager", rollbackFor = {Exception.class}, readOnly = false)
    public void delete(Long productionPlanId) {
        productionPlanRepository.deleteById(productionPlanId);

    }
    
    @Override
    @Transactional(value = "transactionManager", rollbackFor = {Exception.class}, readOnly = false)
    public Integer updateProductionPlan(ProductionPlan productionPlan){
		return productionPlanRepository.updateProductionPlan(productionPlan);
    	
    }
    

}
