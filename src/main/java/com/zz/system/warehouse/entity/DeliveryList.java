package com.zz.system.warehouse.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


/**
 * 出库单表信息
 */

@Entity
@Setter
@Getter
@Table(name = "T_DELIVERY_LIST")
@Proxy(lazy = false)
public class DeliveryList implements Serializable {

    private static final long serialVersionUID = 890537543921549507L;

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /**
     * 主键id
     */
    private Long deliveryListId;

    /**
     * 焊接产品
     */
    private String weldingProducts;

    /**
     *产品编号
     */
    private String productId;

    /**
     *仓库id
     */
    private String warehouseId;

    /**
     *客户
     */
    private String customerId;
    /**
     *数量
     */
    private Long num;

    /**
     *出库日期
     */
    private Date outTime;

    

}
