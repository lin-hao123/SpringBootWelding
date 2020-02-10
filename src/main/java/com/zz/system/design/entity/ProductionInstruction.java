package com.zz.system.design.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;

/**
 * created by linlx on 2020/1/20
 * 生产指导书
 */
@Entity
@Setter
@Getter
@Table(name = "T_Production_Instruction")
@Proxy(lazy = false)
public class ProductionInstruction {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /**
     * 生产指导书编号
     */
    private Long productionInstructionId;

    /**
     * 生产订单
     */
    private String productionOrder;

    /**
     * 焊接方法
     */
    private String weldingMethod;

    /**
     * 焊接设备
     */
    private String weldingEquipment;

    /**
     * 工艺参数
     */
    private String processParameter;

    /**
     * 焊前处理
     */
    private String preWeldingTreatment;

    /**
     * 焊后处理
     */
    private String postWeldProcessing;
}
