package com.zz.system.warehouse.entity;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


/**
 * 领料单表信息
 */
@Entity
@Setter
@Getter
@Table(name = "T_METERIAL_REQUESTION")
@Proxy(lazy = false)
public class MaterialRequestion implements Serializable {

    private static final long serialVersionUID = 890537543921549507L;

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /**
     * 领料单编号
     */
    private Long materialRequestionId;

    /**
     * 用户编号
     */
    private String  userId;

    /**
     * 完成日期
     */
    private Date finishTime;

    /**
     * 创建日期
     */
    private Date createTime;

    /**
     * 材料编号
     */
    private String materialId;

    /**
     * 备注
     */
    private String materialNote;

}
