package com.zz.system.design.service.impl;

import com.zz.system.design.entity.ProductionPlan;
import com.zz.system.design.entity.TaskInstruction;
import com.zz.system.design.repository.TaskInstructionRepository;
import com.zz.system.design.service.TaskInstructionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

/**
 * created by linlx on 2020/1/21
 */

@Service
public class TaskInstructionServiceImpl implements TaskInstructionService {

    @Resource
    TaskInstructionRepository taskInstructionRepository;

    @Override
    public Page<TaskInstruction> findByProductionOrderLike(String productionOrder, int page, int size) {
        Sort sort=new Sort(Sort.Direction.DESC,"createTime");
        Pageable pageable= PageRequest.of(page,size,sort);
        if(productionOrder==null||productionOrder==""){
            productionOrder="%%";
        }else {
            productionOrder="%"+productionOrder+"%";
        }
        return taskInstructionRepository.findByProductionOrderLike(productionOrder,pageable);
    }

    @Override
    public TaskInstruction findById(Long taskInstructionId) {
        return taskInstructionRepository.findById(taskInstructionId).get();
    }

    @Override
    public TaskInstruction create(TaskInstruction taskInstruction) {
        taskInstruction.setCreateTime(new Date());
        return taskInstructionRepository.save(taskInstruction);
    }

    @Override
    @Transactional(value = "transactionManager", rollbackFor = {Exception.class}, readOnly = false)
    public void delete(Long taskInstructionId) {
        taskInstructionRepository.deleteById(taskInstructionId);
    }
    
    @Override
    @Transactional(value = "transactionManager", rollbackFor = {Exception.class}, readOnly = false)
    public Integer updateTaskInstruction(TaskInstruction taskInstruction){
		return taskInstructionRepository.updateTaskInstruction(taskInstruction);
    	
    }
    
}
