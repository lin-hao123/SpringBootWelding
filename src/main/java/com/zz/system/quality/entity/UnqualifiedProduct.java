package com.zz.system.quality.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 不合格产品信息表
 * created by linlx on 2020/2/24
 */

@Entity
@Getter
@Setter
@Table(name = "T_UNQUALIFIED_PRODUCT")
@Proxy(lazy = false)
public class UnqualifiedProduct implements Serializable {

    private static final long serialVersionUID = 6787276131921584863L;

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    /**
     * 不合格产品编号
     */
    private Long unqualifiedProductId;

    /**
     * 产品编号
     */
    private String productionOrder;
    /**
     * 焊接产品
     */
    private String weldingProducts;
    /**
     * 发现时间
     */
    private Date discoveryTime;
    /**
     * 不合格原因
     */
    private String disqualificationReason;
}
