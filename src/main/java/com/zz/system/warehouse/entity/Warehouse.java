package com.zz.system.warehouse.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 仓库表信息
 */
@Entity
@Setter
@Getter
@Table(name = "T_WAREHOUSE")
@Proxy(lazy = false)
public class Warehouse implements Serializable {

    private static final long serialVersionUID = 890537543921549507L;

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /**
     *编号
     */
    private Long warehouseId;

    /**
     *仓库名称
     */
    private String warehouseName;
    /**
     *物料类型
     */
    private String type;
     /**
     *负责人
     */
    private String responsible;

    /**
     *联系电话
     */
    private String warehouseTel;

    /**
     *地址
     */
    private String address;



}
