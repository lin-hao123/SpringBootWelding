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

    /**
     * 设备编号
     */
    private String  deviceId;


    /**
     * 设备名称
     */
    private String deviceName;

    /**
     * 保养内容
     */
    private String maintenanceContent;

    /**
     *负责人
     */
    private String responsible;

    /**
     *保养日期
     */
    private Date maintenanceTime;

}
