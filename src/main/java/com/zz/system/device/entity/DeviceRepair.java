package com.zz.system.device.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Proxy;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.swing.*;
import java.util.Date;

/**
 * created by linlx on 2020/1/16
 */
@Entity
@Setter
@Getter
@Table(name = "T_DEVICE_REPAIR")
@Proxy(lazy = false)
public class DeviceRepair {

    @Id
    @Column

    /**
     * 设备编号
     */
    private String deviceId;

    /**
     * 设备名称
     */
    private String deviceName;

    /**
     * 维修内容
     */
    private String repairContent;

    /**
     * 维修结果
     */
    private String repairResult;

    /**
     * 维修人员
     */
    private String repairStaff;

    /**
     * 维修日期
     */
    private Date repairTime;

}
