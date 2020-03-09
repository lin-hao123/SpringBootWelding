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
    /**
     *编号
     */
    private String materialId;

    /**
     *物料
     */
    private String materialName;

    /**
     *尺寸
     */
    private String materialSize;

    /**
     *仓库
     */
    private String warehouse;
    /**
     *位置
     */
    private String location;
    /**
     *余量
     */
    private Long leftNum;

    /**
     *供应商
     */
    private String supplier;

}
