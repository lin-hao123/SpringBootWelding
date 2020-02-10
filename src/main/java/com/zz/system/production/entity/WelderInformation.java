package com.zz.system.production.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;


/**
 * created by linlx on 2020/1/21
 * 焊工信息表
 */
@Entity
@Setter
@Getter
@Table(name = "T_WELDER_INFORMATION")
public class WelderInformation {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /**
     * 焊工编号
     */
    private Long welderId;

    /**
     * 焊工姓名
     */
    private String welderName;
    /**
     * 联系方式
     */
    private String welderTel;
    /**
     * 性别
     */
    private String sex;
    /**
     * 出生日期
     */
    private Date birthday;
    /**
     * 住址
     */
    private String address;
    /**
     * 培训信息
     */
    private String trainingInformation;
    /**
     * 创建日期
     */
    private Date createTime;
    /**
     * 更新日期
     */
    private Date updateTime;

}
