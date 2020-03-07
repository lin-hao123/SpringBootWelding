package com.zz.system.production.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * created by linlx on 2020/2/25
 * 设备监控信息表
 */

@Entity
@Setter
@Getter
@Table(name = "T_EQUIPMENT")
public class Equipment implements Serializable {

    private static final long serialVersionUID = 6787276131921584863L;

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /**
     * 设备号
     */
    private Long equipmentId;

    /**
     * 设备名称
     */
    private String equipmentName;
    /**
     * 设备位置
     */
    private String equipmentLocation;
    /**
     * 焊接种类
     */
    private String weldingType;
    /**
     * 使用状态
     */
    private String useStatus;
    /**
     * 焊接电压
     */
    private String weldingVoltage;
    /**
     * 焊接电流
     */
    private String weldingCurrent;
    /**
     * 焊接速度
     */
    private String weldingSpeed;

}
