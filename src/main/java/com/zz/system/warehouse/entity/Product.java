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
    /**
     *产品编号
     */
    private String productId;

    /**
     *产品名称
     */
    private String productName;
    /**
     *数量
     */
    private Long num;

    /**
     *仓库
     */
    private String warehouse;
    /**
     *位置
     */
    private String location;
    /**
     *客户名
     */
    private String customer;
    /**
     *入库/出库
     */
    private int status;
    /**
     *入库/出库编号
     */
    private String entryListId;
    /**
     *时间
     */
    private Date finishedTime;

}
