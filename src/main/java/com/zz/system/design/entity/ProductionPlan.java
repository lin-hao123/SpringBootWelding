package com.zz.system.design.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.util.Date;

/**
 * created by linlx on 2020/1/20
 * 生产计划
 */

@Entity
@Setter
@Getter
@Table(name="T_Production_plan")
@Proxy(lazy = false)
public class ProductionPlan {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /**
     * 生产计划编号
     */
    private Long productionPlanId;

    /**
     * 产品规格
     */
    private String specification;

    /**
     * 产品名称
     */
    private String productName;

    /**
     *生产订单
     */
    private String productionOrder;

    /**
     * 计划数量
     */
    private Long num;

    /**
     * 交货日期
     */
    private Date deliveryDate;

    /**
     * 创建日期
     */
    private Date createTime;

    /**
     * 更新日期
     */
    private Date updateTime;




 }
