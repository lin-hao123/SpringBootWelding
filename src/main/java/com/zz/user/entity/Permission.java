
package com.zz.user.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;
import lombok.Getter;
import lombok.Setter;

/**
 * @Description: 
 * @Author asuslh
 * @DateTime 2019年10月28日 下午5:08:57 
 */
@Entity
@Getter
@Setter
@Table(name="T_PERMISSION")
@Proxy(lazy = false)
public class Permission implements Serializable{

	/**
	  * @Fields serialVersionUID : 
	  */
	
	private static final long serialVersionUID = 7096177578064759974L;
	
	    @Id
	    @Column(length = 50)
	 	/*
	 	 * 编号
	 	 */
	    private String id;
	 	/*
	 	 * url地址
	 	 */
	    private String url;
	    /*
	     * 描述
	     */
	    private String name;


}
