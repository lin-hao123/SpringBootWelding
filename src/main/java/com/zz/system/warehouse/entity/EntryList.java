package com.zz.system.warehouse.entity;

import com.sun.org.apache.xerces.internal.impl.xpath.XPath;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


/**
 * 入库单表信息
 */
@Entity
@Getter
@Setter
@Table(name = "T_ENTRY_LIST")
@Proxy(lazy = false)
public class EntryList implements Serializable {

    private static final long serialVersionUID = 890537543921549507L;

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /**
     *主键id
     */
    private Long entryListId;

    /**
     *材料id
     */
    private String materialId;

    /**
     *用户id
     */
    private String userId;

    /**
     *数量
     */
    private Long num;

    private String note;

    /**
     *入库日期
     */
    private Date entryTime;

    /**
     *产品id
     */
    private String productId;

    /**
     *仓库id
     */
    private String warehouseId;

    /**
     *供应商id
     */
    private String supplierId;

}
