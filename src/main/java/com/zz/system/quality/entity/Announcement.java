package com.zz.system.quality.entity;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


/**
 * 公告信息表
 * created by linlx on 2020/2/24
 */

@Entity
@Getter
@Setter
@Table(name = "T_ANNOUNCEMENT")
@Proxy(lazy = false)
public class Announcement implements Serializable {

    private static final long serialVersionUID = 6787276131921584863L;

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /**
     * 公告编号
     */
    private Long announcementId;

    /**
     * 公告内容
     */
    private String announcementContent;

    /**
     * 发布日期
     */
    private Date createTime;

    /**
     * 修改日期
     */
    private Date updateTime;

}
