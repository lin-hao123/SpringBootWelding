package com.zz.system.production.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * created by linlx on 2020/1/21
 * 设备维修申请表信息
 */
@Entity
@Setter
@Getter
@Table(name = "T_EQUIPMENT_MAINTENANCE_APPLICATION")
public class EquipmentMaintenanceApplication {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /**
     * 设备维修申请编号
     */
    private Long equipmentMaintenanceApplicationId;
    /**
     * 设备编号
     */
    private Long deviceId;
    /**
     * 设备名称
     */
    private String deviceName;
    /**
     * 责任人
     */
    private String responsible;
    /**
     * 申请原因
     */
    private String applyReason;
    /**
     * 申请时间
     */
    private Date applyTime;
    /**
     * 状态
     */
    private String status;

}
