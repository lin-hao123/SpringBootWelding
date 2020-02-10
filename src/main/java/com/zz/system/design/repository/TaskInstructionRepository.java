package com.zz.system.design.repository;

import com.zz.system.design.entity.TaskInstruction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * created by linlx on 2020/1/21
 */
public interface TaskInstructionRepository extends JpaRepository<TaskInstruction,Long> {

    Page<TaskInstruction> findByProductionOrderLike(String productionOrder, Pageable pageable);
}
