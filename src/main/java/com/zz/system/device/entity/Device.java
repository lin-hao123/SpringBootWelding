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
@Table(name = "T_DEVICE")
public class Device {

    @Id
    /**
     * 设备编号
     */
    @Column
    private String deviceId;

    /**
     * 设备名称
     */
    private String deviceName;


    /**
     * 焊接种类
     */
    private String weldingType;

    /**
     * 位置
     */
    private String location;

    /**
     * 厂商
     */
    private String vendor;

    /**
     * 厂商联系方式
     */
    private String vendorTel;

    /**
     * 购买时间
     */
    private Date purchaseTime;

    /**
     * 负责人
     */
    private String responsible;
}
