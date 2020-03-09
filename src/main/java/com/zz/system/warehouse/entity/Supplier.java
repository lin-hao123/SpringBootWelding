package com.zz.system.warehouse.entity;

import jdk.nashorn.internal.runtime.logging.Logger;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.io.Serializable;

/**
 *供货商表信息
 */
@Entity
@Setter
@Getter
@Table(name = "T_SUPPLIER")
@Proxy(lazy = false)
public class Supplier implements Serializable {

    private static final long serialVersionUID = 890537543921549507L;

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /**
     *编号
     */
    private Long supplierId;
    /**
     *供应商名称
     */
    private String supplierName;
    /**
     *物料
     */
    private String material;
    /**
     *负责人
     */
    private String responsible;

    /**
     *联系方式
     */
    private String supplierTel;

    /**
     *地址
     */
    private String address;
    /**
     *备注
     */

    private String note;




}
