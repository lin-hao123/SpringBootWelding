package com.zz.system.quality.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "T_MATERIAl_INSPECTION")
@Proxy(lazy = false)
/**
 * 质量检测信息表
 * created by linlx on 2020/2/24
 */
public class MaterialInspection implements Serializable {

    private static final long serialVersionUID = 6787276131921584863L;

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    /**
     * 物料检测Id
     */
    private Long materialInspectionId;
    /**
     * 产品编号
     */
    private String weldingConsumablesId;

    /**
     * 焊接产品
     */
    private String weldingConsumables;
    /**
     * 检测数量
     */
    private Long detectionQuantity;

    /**
     * 合格数量
     */
    private Long qualifiedQuantity;
    /**
     * 备注：
     */
    private String note;
    /**
     * 检测人员
     */
    private String inspectionStaff;

    
    /**
     * 检测时间
     */
    private Date checkedTime;


}
