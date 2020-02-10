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
     *主键id
     */
    private Long warehouseId;

    /**
     *用户id
     */
    private String userId;

    /**
     *仓库名称
     */
    private String warehouseName;

    /**
     *练习方式
     */
    private String warehouseTel;

    /**
     *地址
     */
    private String address;

    private Date createTime;

    private Date updateTime;


}
