package com.zz.system.warehouse.entity;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.io.Serializable;


/**
 * 材料表信息
 */
@Entity
@Setter
@Getter
@Table(name = "T_MATERIAL")
@Proxy(lazy = false)
public class Material implements Serializable {

    private static final long serialVersionUID = 890537543921549507L;

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /**
     *主键id
     */
    private Long materialId;

    /**
     *材料名称
     */
    private String materialName;

    /**
     *大小
     */
    private String materialSize;

    /**
     *
     */
    private String location;

    /**
     *价格
     */
    private double materialPrice;

    /**
     *数量
     */
    private Long num;

    private String note;

    /**
     *入库单id
     */
    private String entryListId;

    /**
     *供应商id
     */
    private String supplierId;

}
