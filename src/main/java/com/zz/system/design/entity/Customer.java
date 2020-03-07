package com.zz.system.design.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.io.Serializable;
import java.security.acl.LastOwnerException;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "T_CUSTOMER")
@Proxy(lazy = false)

/**
 * 顾客信息表
 * create by linlx
 */
public class Customer implements Serializable {

    private static final long serialVersionUID = 6787276131921584863L;

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    /**
     * 顾客编号
     */
    private Long customerId;

    /**
     * 顾客姓名
     */
    private String customerName;

    /**
     * 产品编号
     */
    private String productId;

    /**
     * 联系方式
     */
    private String customerTel;

    /**
     * 地址
     */
    private String address;
    /**
     * 性别
     */
    private String sex;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

}
