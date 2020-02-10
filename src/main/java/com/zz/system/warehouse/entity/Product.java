package com.zz.system.warehouse.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 产品表信息
 */
@Entity
@Getter
@Setter
@Table(name = "T_PRODUCT")
@Proxy(lazy = false)
public class Product implements Serializable {

    private static final long serialVersionUID = 6787276131921584863L;

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /**
     *产品id
     */
    private Long productId;

    /**
     *产品名称
     */
    private String productName;

    /**
     *产品数量
     */
    private Long num;

    private Date deadTime;

    /**
     *状态 入库/出库
     */
    private int status;

    private String note;

    /**
     *顾客id
     */
    private String customerId;

    /**
     *仓库id
     */
    private String warehouseId;

    /**
     *入库单id
     */
    private String entryListId;

    /**
     *用户id
     */
    private String userId;

    /**
     *价格
     */
    private double money;

    private String location;

    private String size;

    /**
     *创建时间
     */
    private Date createTime;

}
