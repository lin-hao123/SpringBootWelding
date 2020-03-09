package com.zz.system.warehouse.entity;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


/**
 * 领料单表信息
 */
@Entity
@Setter
@Getter
@Table(name = "T_METERIAL_REQUESTION")
@Proxy(lazy = false)
public class MaterialRequestion implements Serializable {

    private static final long serialVersionUID = 890537543921549507L;

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /**
     * 编号
     */
    private Long materialRequestionId;
    /**
     * 申请部门
     */
    private String  department;
    /**
     * 申请人
     */
    private String  user;
    /**
     * 物料
     */
    private String material;

    /**
     * 创建日期
     */
    private Date createTime;

    /**
     * 完成
     */
    private String finished;

    /**
     * 备注
     */
    private String materialNote;

}
