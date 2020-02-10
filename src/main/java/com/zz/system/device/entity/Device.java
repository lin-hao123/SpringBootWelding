package com.zz.system.device.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;


/**
 * created by linlx on 2020/1/16
 */

@Entity
@Setter
@Getter
@Table(name = "T_DEVICE")
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /**
     * 设备编号
     */
    @Column
    private Long deviceId;

    /**
     * 设备名称
     */
    private String deviceName;

    /**
     * 厂商
     */
    private String vendor;

    /**
     * 厂商联系方式
     */
    private String vendorTel;

    /**
     * 焊接种类
     */
    private String weldingType;

    /**
     * 所在部门
     */
    private String department;

    /**
     * 功能作用
     */
    private String deviceFunction;

    /**
     * 责任人
     */
    private String responsible;
}
