package com.zz.system.design.service.impl;

import com.zz.system.design.entity.ProductionInstruction;
import com.zz.system.design.repository.ProductionInstructionRepository;
import com.zz.system.design.service.ProductionInstructionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * created by linlx on 2020/1/20
 */
@Service
public class ProductionInstructionServiceImpl implements ProductionInstructionService {

    @Resource
    ProductionInstructionRepository productionInstructionRepository;

    @Override
    public Page<ProductionInstruction> findByProductionOrderLike(String productionOrder, int page, int size) {
        Sort sort=new Sort(Sort.Direction.DESC,"productionInstructionId");
        Pageable pageable= PageRequest.of(page,size,sort);
        if(productionOrder==null||productionOrder==""){
            productionOrder="%%";
        }else {
            productionOrder="%"+productionOrder+"%";
        }
        return productionInstructionRepository.findByProductionOrderLike(productionOrder,pageable);
    }

    @Override
    public ProductionInstruction findById(Long productionInstructionId) {
        return productionInstructionRepository.findById(productionInstructionId).get();
    }

    @Override
    public ProductionInstruction create(ProductionInstruction productionInstruction) {
        return productionInstructionRepository.save(productionInstruction);
    }

    @Override
    public void delete(Long productionInstructionId) {
        productionInstructionRepository.deleteById(productionInstructionId);
    }
}
