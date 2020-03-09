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
     *编号
     */
    private Long entryListId;

    /**
     *焊接产品
     */
    private String weldingProducts;

    /**
     *入库数量
     */
    private Long num;
    /**
     *仓库
     */
    private String warehouse;

    /**
     *供应商
     */
    private String supplier;
    /**
     *入库日期
     */
    private Date entryTime;

}
