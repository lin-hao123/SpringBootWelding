package com.zz.system.design.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.util.Date;

/**
 * created by linlx on 2020/1/21
 * 任务指令表信息
 */
@Entity
@Setter
@Getter
@Table(name = "T_TASK_INSTRUCTION")
@Proxy(lazy = false)
public class TaskInstruction {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /**
     * 任务指令编号
     */
    private Long taskInstructionId;

    /**
     * 产品编号
     */
    private String productionOrder;

    /**
     * 产品名称
     */
    private String productName;

    /**
     * 生产数量
     */
    private Long num;

    /**
     * 生产部门
     */
    private String productionDepartment;

    /**
     * 焊材型号
     */
    private String weldingMaterialId;

    /**
     * 焊材名称
     */
    private String weldingMaterialName;

    /**
     * 焊材用量
     */
    private Long weldingMaterialNum;

    /**
     * 当前库存
     */
    private Long currentInventory;

    /**
     * 创建时间
     */
    private Date createTime;

}
