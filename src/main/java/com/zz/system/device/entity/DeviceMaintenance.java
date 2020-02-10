package com.zz.system.device.entity;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.util.Date;

/**
 * created by linlx on 2020/1/16
 */
@Entity
@Setter
@Getter
@Table(name = "T_DEVICE_MAINTENANCE")
@Proxy(lazy = false)
public class DeviceMaintenance {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /**
     * 设备编号
     */
    private Long  deviceId;

    /**
     * 设备型号
     */
    private String deviceModel;

    /**
     * 设备名称
     */
    private String deviceName;

    /**
     * 保养内容
     */
    private String maintenanceContent;

    /**
     *责任部门
     */
    private String department;

    /**
     *责任人
     */
    private String responsible;

    /**
     *保养日期
     */
    private Date maintenanceTime;

}
