package com.zz.system.production.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.util.Date;

/**
 * created by linlx on 2020/1/22
 * 派工信息表
 */

@Entity
@Setter
@Getter
@Table(name = "T_DISPATCH_INFORMATION")
@Proxy(lazy = false)
public class DispatchInformation {


    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /**
     * 派工编号
     */
    private Long dispatchId;
    /**
     * 生产单号
     */
    private Long productionId;
    /**
     * 产品名称
     */
    private String productName;
    /**
     * 任务量
     */
    private String taskVolume;
    /**
     * 开工日期
     */
    private Date startTime;
    /**
     * 完工日期
     */
    private Date finishTime;
    /**
     * 生产指导书
     */
    private String productionInstruction;
    /**
     * 生产量
     */
    private String production;
    /**
     * 交货日期
     */
    private Date deliveryTime;

}
