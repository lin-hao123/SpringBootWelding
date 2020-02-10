package com.zz.system.design.service;

import com.zz.system.design.entity.TaskInstruction;
import org.springframework.data.domain.Page;

/**
 * created by linlx on 2020/1/21
 */
public interface TaskInstructionService {

    Page<TaskInstruction> findByProductionOrderLike(String productionOrder,int page,int size);

    TaskInstruction findById(Long taskInstructionId);

    TaskInstruction create(TaskInstruction taskInstruction);

    void delete(Long taskInstructionId);
}
